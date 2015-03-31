package nl.will.apachedesk.server;

import java.util.ArrayList;
import java.util.List;

public class SolutionService {

	public SolutionService() {
		super();
	}

	public List<Solution> selectSolutions(String search) {
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		for (int i = 0; i < 10; i++) {
			solutions.add(new Solution("solution " + 1, Solution.LOREM_IPSUM, 10 * i));

		}

		return solutions;
	}

}
