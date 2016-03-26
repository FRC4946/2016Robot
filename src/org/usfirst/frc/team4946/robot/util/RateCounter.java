package org.usfirst.frc.team4946.robot.util;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class RateCounter extends Counter implements PIDSource {
	final double MIN_TIME = 1;

	private double[] m_history = { 0, 0, 0, 0, 0 };
	private double m_maxVal = 1.0;

	public RateCounter(int channel) {
		super(channel);
		setMaxPeriod(MIN_TIME);
		// setSamplesToAverage(5);
	}
	
	public void setMaxVal(double val){
		m_maxVal = val;
	}

	/**
	 * Returns the time between the last two ticks, as a raw RPM counter.
	 */
	public double getRPM() {
		getInstantRPM();

		double avg = 0;
		for (int i = 0; i < m_history.length; i++) {
			avg += m_history[i];
		}

		avg /= m_history.length;

		return avg;
	}

	public double getInstantRPM() {
		double period = getPeriod();
		double rate = 0;

		if (period < MIN_TIME && period > 0) // At least 60rpm
			rate = 1.0 / period * 60.0;

		for (int i = 0; i < m_history.length-1; i++) {
			m_history[i] = m_history[i + 1];
		}

		m_history[m_history.length - 1] = rate;
		return rate;
	}

	public double pidGet() {
		return getRPM() / m_maxVal;
	}

	@Override
	// Do nothing with this method as it is not used.
	public void setPIDSourceType(PIDSourceType pidSource) {
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kRate;
	}

}
