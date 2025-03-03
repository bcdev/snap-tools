package eu.esa.snap.performance.testImplementation;

import eu.esa.snap.performance.actions.Action;
import eu.esa.snap.performance.actions.MultipleExecutionsAction;
import eu.esa.snap.performance.actions.ReadProductFullyAction;
import eu.esa.snap.performance.util.Parameters;
import eu.esa.snap.performance.util.TestUtils;

import java.util.logging.Logger;

public class ReadPerformanceTest extends PerformanceTest {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public ReadPerformanceTest(String testName, Parameters params) {
        super(testName, params);
    }

    @Override
    public void execute() throws Throwable {
        logger.info("Execution of " + getTestName() + " started....");

        String productName1 = getParameters().getProducts().get(0);
        String productName2 = getParameters().getProducts().get(1);
        String testDir = getParameters().getTestDir();

        Action baseAction1 = new ReadProductFullyAction(productName1, testDir);
        Action baseAction2 = new ReadProductFullyAction(productName2, testDir);

        Action measurementActions1 = TestUtils.constructMeasurementActionsPipeline(baseAction1, getParameters());
        Action measurementActions2 = TestUtils.constructMeasurementActionsPipeline(baseAction2, getParameters());

        Action multipleExecutions1 = new MultipleExecutionsAction(measurementActions1, getParameters());
        Action multipleExecutions2 = new MultipleExecutionsAction(measurementActions2, getParameters());

        multipleExecutions1.execute();
        multipleExecutions2.execute();

        setResult1(multipleExecutions1.fetchResults());
        setResult2(multipleExecutions2.fetchResults());

        logger.info("Execution of " + getTestName() + " finished :)");
    }
}
