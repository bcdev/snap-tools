package eu.esa.snap.performance.testImplementation;

import eu.esa.snap.performance.util.Parameters;
import eu.esa.snap.performance.util.PerformanceTestResult;
import eu.esa.snap.performance.util.Result;
import eu.esa.snap.performance.util.TestUtils;

import java.util.List;

public abstract class PerformanceTest {

    private final String testName;
    private final Parameters parameters;

    private List<Result> result1;
    private List<Result> result2;

    public PerformanceTest(String testName, Parameters parameters) {
        this.testName = testName;
        this.parameters = parameters;
    }

    public abstract void execute() throws Throwable;

    public PerformanceTestResult fetchResults() {
        return TestUtils.combineResults(this.result1, this.result2, this.parameters, this.testName);
    }

    public String getTestName() {
        return testName;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setResult1(List<Result> result1) {
        this.result1 = result1;
    }

    public void setResult2(List<Result> result2) {
        this.result2 = result2;
    }
}
