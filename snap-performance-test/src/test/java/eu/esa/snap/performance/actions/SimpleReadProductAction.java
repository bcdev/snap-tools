package eu.esa.snap.performance.actions;

import org.esa.snap.core.dataio.ProductIO;
import org.esa.snap.core.datamodel.Product;
import eu.esa.snap.performance.util.Result;
import eu.esa.snap.performance.util.TestUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SimpleReadProductAction implements Action {

    private final String productName;
    private String testDataDir;
    private Product result;
    private List<Result> allResults;

    public SimpleReadProductAction(String productName, String testDataDir) {
        this.productName = productName;
        this.testDataDir = testDataDir;
    }

    @Override
    public void execute() throws Throwable {
        this.allResults = new ArrayList<>();
        String fullFilePath = TestUtils.buildProductPathString(this.testDataDir, this.productName);
        Product product = ProductIO.readProduct(new File(fullFilePath));
        this.result = product;

        Result result1 = new Result("Product", false, product, "");
        Result result2 = new Result("Format", false, product.getProductReader().getReaderPlugIn().getFormatNames()[0], "");
        this.allResults.add(result1);
        this.allResults.add(result2);
    }

    @Override
    public void cleanUp() {
        this.result.dispose();
    }

    @Override
    public List<Result> fetchResults() {
        return this.allResults;
    }
}
