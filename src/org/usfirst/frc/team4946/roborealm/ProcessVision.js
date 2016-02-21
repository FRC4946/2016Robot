/**
 * Verify the position of the coordinates [x,y].
 * 
 * Ensure the specified coordinates are not within 10px of the edge of the
 * frame.
 * 
 * @returns true if the coords are valid
 */
function isWithinRange(x, y) {

	var border = 15;

	if (y > (GetVariable("IMAGE_HEIGHT") - border) || y < border) {
		return false;
	}
	if (x > (GetVariable("IMAGE_WIDTH") - border) || x < border) {
		return false;
	}
	return true;
}

/**
 * Calculate the slope of a line from point [startX, startY] to [endX, endY]
 * 
 * @returns the slope of the line (double)
 */
function calculateSlope(startX, startY, endX, endY) {
	var rise = endY - startY;
	var run = endX - startX;
	return rise / run;
}

/**
 * Calculate the x intercept of the line from point [startX, startY] to [endX,
 * endY]
 * 
 * @returns the x val of the x intercept (double)
 */
function calculateIntercept(startX, startY, endX, endY) {
	var slope = calculateSlope(startX, startY, endX, endY);
	return ((slope * startX) - startY) * -1;
}

/**
 * Calculate the distance from [pX, pY] to the line [startX, startY]-[endX,
 * endY]
 * 
 * @returns the distance (double)
 */
function getPointDistance(pX, pY, startX, startY, endX, endY) {

	// Calculate the eqn of the line [startX, startY] [endX, endY]
	var slope = calculateSlope(startX, startY, endX, endY);
	var intercept = calculateIntercept(startX, startY, endX, endY);

	// Calcualte the eqn of the perpendicular line that intersects the set point
	var inverseSlope = -1 / slope;
	var inverseIntercept = ((inverseSlope * pX) - pY) * -1;

	// Calculate the intersection point of the two lines, and the distance from
	// that intersection point to the setpoint
	var xInt = (intercept - inverseIntercept) / (inverseSlope - slope);
	var yInt = (slope * xInt) + intercept;
	var distance = sqrt((pX - xInt) * (pX - xInt) + (pY - yInt) * (pY - yInt));

	// If the setpoint is above the specified line, multiple the distance by -1
	if (pY < (slope * pX + intercept)) {
		distance *= -1;
	}

	return distance;
}

// =*=*=*=*=*= MAIN ENTRY POINT =*=*=*=*=*= \\

// Get the array of corners from RoboRealm
var hcorners = GetArrayVariable("HARRIS_CORNERS");
var rcorners = GetArrayVariable("RING_CORNER");
var length = hcorners.length + rcorners.length;

// If we got less than 8 corners, return
if (length < 8) {
	Write("Not enough corners");
	SetVariable("TOP_LEFT_X", -1);
	SetVariable("TOP_LEFT_Y", -1);
	SetVariable("TOP_RIGHT_X", -1);
	SetVariable("TOP_RIGHT_Y", -1);
	SetVariable("BOT_LEFT_X", -1);
	SetVariable("BOT_LEFT_Y", -1);
	SetVariable("BOT_RIGHT_X", -1);
	SetVariable("BOT_RIGHT_Y", -1);
	SetVariable("HAS_CORNERS", false);
}

// Otherwise, start doing calculations
else {
	SetVariable("HAS_CORNERS", true);
	SetVariable("EDGE_PROXIMITY", false);

	// Pull all of the points from the RoboRealm to a local JS array
	for (var i = 0; i < hcorners.length; i++) {
		hcorners[i] = GetVariable("HARRIS_CORNERS:" + i);
	}
	for (var i = 0; i < rcorners.length; i++) {
		rcorners[i] = GetVariable("RING_CORNER:" + i);
	}
	
	var corners = hcorners.concat(rcorners);
	
	// Get the first and the last point to use as the initial line
	var startX = corners[0];
	var startY = corners[1];
	var endX = corners[length - 1];
	var endY = corners[length - 2];

	// Declare a handful of variables to store the points
	var topLeftX = 0;
	var topRightX = 0;
	var botLeftX = 0;
	var botRightX = 0;
	var topLeftY = 0;
	var topRightY = 0;
	var botLeftY = 0;
	var botRightY = 0;

	// Loop
	var iterations = 10;
	for (var loop = 0; loop < iterations; loop++) {

		var maxDistance = 0;
		var minDistance = 0;
		var maxX = 0;
		var maxY = 0;
		var minX = 0;
		var minY = 0;

		// Iterate through every point
		for (var i = 0; i < length; i += 2) {

			var distance = getPointDistance(corners[i], corners[i + 1], startX,
					startY, endX, endY);

			if (distance > maxDistance) {
				maxDistance = distance;
				maxX = corners[i];
				maxY = corners[i + 1];
			} else if (distance < minDistance) {
				minDistance = distance;
				minX = corners[i];
				minY = corners[i + 1];
			}
		}

		startX = maxX;
		startY = maxY;
		endX = minX;
		endY = minY;

		if (loop == iterations-2) {

			// The greater XVal will always be on the right
			if (maxX > minX) {
				topRightX = maxX;
				topRightY = maxY;
				botLeftX = minX;
				botLeftY = minY;
			} else {
				topLeftX = maxX;
				topLeftY = maxY;
				botRightX = minX;
				botRightY = minY;
			}
		} else if (loop == iterations-1) {

			// The greater XVal will always be on the right
			if (topRightX == 0) {
				topRightX = maxX;
				topRightY = maxY;
				botLeftX = minX;
				botLeftY = minY;
			} else {
				topLeftX = maxX;
				topLeftY = maxY;
				botRightX = minX;
				botRightY = minY;
			}
		}

		if (loop >= 1) {
			var num = (loop - 1) * 2

			if (!isWithinRange(maxX, maxY) || !isWithinRange(minX, minY)) {
				SetVariable("EDGE_PROXIMITY", true);
			}

		}
	}

	SetVariable("TOP_LEFT_X", topLeftX);
	SetVariable("TOP_LEFT_Y", topLeftY);
	SetVariable("TOP_RIGHT_X", topRightX);
	SetVariable("TOP_RIGHT_Y", topRightY);
	SetVariable("BOT_LEFT_X", botLeftX);
	SetVariable("BOT_LEFT_Y", botLeftY);
	SetVariable("BOT_RIGHT_X", botRightX);
	SetVariable("BOT_RIGHT_Y", botRightY);

	//Write("TL: (" + topLeftX + "," + topLeftY + ")\n");
	//Write("TR: (" + topRightX + "," + topRightY + ")\n");
	//Write("BL: (" + botLeftX + "," + botLeftY + ")\n");
	//Write("BR: (" + botRightX + "," + botRightY + ")\n");

}