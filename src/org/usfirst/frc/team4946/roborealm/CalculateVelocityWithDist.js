	var MAX_RPM = 10400;
	var MAX_VEL = 55.325541024818655;
	var NAN_ERROR = -1;
	var TOO_CLOSE_ERROR = -2;
	var TOO_FAR_ERROR = -3;
	
	/**
	 * Calculates velocity given a distance from the target
	 * @param x The lateral distance from the target, flat on the x-axis.
	 * @return The 
	 */
	function calcVelocity(x) {
		if (x < 1.2685) {
			return TOO_CLOSE_ERROR;
		} else if (x > 20) {
			return TOO_FAR_ERROR;
		} else {
			return newtonsMethod(findInitialEstimate(x), x);
		}
	}

	/**
	 * Finds an integer initial velocity for accurate use of 
	 * Newton's Method.
	 * 
	 * @param x The distance from the target.
	 * @return The closest integer to the root on the positive side 
	 * (an overshoot)
	 */
	function findInitialEstimate(x) {
		
		// Until the v0 barely undershoots the goal
		for (var v0 = Math.ceil(MAX_VEL); v0 - 0.5 >= 0; v0 -= 0.5) {
			var y = f(v0, x);
			
			// When the error is finally negative
			if (y < 0) {
				return v0;
			}
		}
		
		// If nothing works, 9 is good enough
		return 9;
	}
	
	function newtonsMethod(v0, x) {
		
		var tolerance = 0.000000001;

		// Iterate Newton's Method until it is within the tolerance
		while ( Math.abs( trajectoryError(v0, x)) > tolerance) {
			v0 = iteration(v0, x, tolerance);
		}
		
		return v0;
	}
	
	/**
	 * An iteration of Newton's method.
	 * @param v0 The approximation of the root.
	 * @param x The distance from the goal.
	 * @param tolerance
	 * @return
	 */
	function iteration(v0, x, tolerance) {
		return v0 - f(v0, x) / fprime(v0, x);
	}
	
	/**
	 * The function that gives the vertical error given an 
	 * initial velocity and a position.
	 * @param v0 The initial velocity.
	 * @param x The position.
	 * @return The horizontal offset.
	 */
	function trajectoryError(v0, x) {
		var phi = Math.PI/180.0* 60.0;
		var k = 0.04947459275363921;
		var g = 9.807;
		var a = (Math.exp(k * x)-1) * Math.sqrt(g/k) / Math.cos(phi);
		var h = 2.4384 - 0.288544;
		
		return Math.log(v0 * Math.sin(phi) * Math.sqrt(k / g) * Math.sin(a/v0) + Math.cos(a/v0)) / k - h;
	}

	/**
	 * A function whose root is the v0 
	 * required for an error of 0.
	 * 
	 * @param v0 The initial velocity.
	 * @param x The distance on the x-axis.
	 * @return +ve if the ball will overshoot the goal, -ve if the ball undershoots.
	 */
	function f(v0, x) {
		var phi = Math.PI/180.0* 60.0;
		var k = 0.04947459275363921;
		var g = 9.807;
		var a = (Math.exp(k * x)-1) * Math.sqrt(g/k) / Math.cos(phi);
		
		/* Height of target subtract height of shooter
		 * aka the relative height of the target.		*/
		var h = 2.4384 - 0.288544;

		return v0 * Math.sin(phi) * Math.sqrt(k / g) * Math.sin(a/v0) + Math.cos(a/v0) - Math.exp(h*k) ;
	}
	
	/**
	 * The derivative with respect to velocity, solved analytically with Wolfram|Alpha
	 * http://www.wolframalpha.com/input/?i=derivative+x+*+b+*+sin(a%2Fx)+%2B+cos(a%2Fx)
	 * 
	 * @param v0 The velocity.
	 * @param x The distance.
	 * @return The slope at the point.
	 */
	function fprime(v0, x) {
		var phi = Math.PI/180.0* 60.0;
		var k = 0.04947459275363921;
		var g = 9.807;
		var a = (Math.exp(k * x)-1) * Math.sqrt(g/k) / Math.cos(phi);
		var b = Math.sin(phi) * Math.sqrt(k / g);
		return (a/(v0*v0) + b) * Math.sin(a/v0) - a*b*Math.cos(a/v0) / v0;
	}


	// ENTRY POINT
	var dist = GetVariable("TL_TARGET_DISTANCE")/100.0;
	dist = (4.6878*dist) - 99.571;
	SetVariable("DISTANCE_INCHES", dist);
	dist *= 0.0254;
	SetVariable("DISTANCE_FINAL", dist);

	var vel = calcVelocity(dist);
	SetVariable("VELOCITY_FINAL", vel);

	var rpm = MAX_RPM / MAX_VEL * vel;
	SetVariable("RPM_FINAL", rpm);

