/*  Copyright 2019 Balázs Zaicsek
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package io.github.zebalu.gradle.teavm;

import org.slf4j.Logger;
import org.teavm.vm.TeaVMPhase;
import org.teavm.vm.TeaVMProgressFeedback;
import org.teavm.vm.TeaVMProgressListener;

public class LoggingProgressListener implements TeaVMProgressListener {

	private final Logger log;
	private double target = 1.0;
	private TeaVMPhase currentPhase;

	public LoggingProgressListener(Logger log) {
		this.log = log;
	}

	@Override
	public TeaVMProgressFeedback phaseStarted(TeaVMPhase phase, int maxSteps) {
		log.info("TeaVM: Progress, phase: {} started, targeted steps: {}", phase, (int)maxSteps);
		target = maxSteps;
		currentPhase = phase;
		return TeaVMProgressFeedback.CONTINUE;
	}

	@Override
	public TeaVMProgressFeedback progressReached(int stepsReached) {
		log.info("TeaVM: {}; progress reached: {} of {} -- {}%", currentPhase, stepsReached, (int)target, (int)(Math.round(stepsReached/target*100.0)));
		return TeaVMProgressFeedback.CONTINUE;
	}

}
