//class Newton  {
// var g = 9.81; // Gravity. m/s^2
// var vt = 14.0791674308; // Terminal Velocity
var vt2 = 198.222955545; // Terminal Velocity Squared
var vt4_g = 4006.56063066; // Terminal Velocity^4 divided by Gravity
var vt2_g = 20.2123947736; // Terminal Velocity Squared divided by Gravity

var hRobot = 0.288544;
var hTarget = 2.4384;


/**
 * Height as a function of initial velocity
 * 
 * @param v0
 *            the initial velocity
 * @param distance
 *            is the distance from the target
 * @returns the height as a function of v0
 */
function height(var v0, var distance){

	var term1 = (-2*vt4_g*Math.exp(distance/vt2_g)+2*vt4_g)/v0;
	var term2 = (vt2_g*Math.sqrt(3)/2*v0)+vt4_g;
	var term4 = (-2*Math.exp(distance/vt2_g)+2)/v0;
	var term3 = 1-Math.exp(term4);
	var height = hRobot - hTarget;


	return term1 + (term2 * term3) + height;
}

/**
 * Derivative of height(v0)
 * 
 * @param v0
 *            the initial velocity
 * @param distance
 *            is the distance from the target
 * @returns the slope of the function
 */
function heightPrime(var v0, var distance){
	var delta = 0.01;

	var term1 = (height(v0+delta, distance) - height(v0, distance)) / delta;
	return term1;
}

/**
 * Function to iterate
 */
function f(var v0, var distance) {

	var term1 = v0-(height(v0, distance)/heightPrime(v0, distance));

	return term1;
}

/**
 * Approximates v0 based on the distance.
 * 
 * @param v0
 *            the initial guess of v0
 * @returns the approximation of v0
 */
function newtonMethod(var tolerance, var v0, var distance){

	// Set a max count for the number of iterations of newton's method
	var max_count = 100;

	for( var i = 1; (Math.abs(f(v0, distance)) > tolerance) && ( i < max_count); i ++)  {
		v0 = f(v0, distance);
	}            

	if( Math.abs(f(v0, distance)) <= tolerance) {
		System.out.println("Zero found at x="+v0);
		return v0;
	} else {
		System.out.println("Failed to find a zero");
		return -1;
	}
}




//}
