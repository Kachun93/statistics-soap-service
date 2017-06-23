package nl.hu.iac.ws;

import nl.hu.iac.wsinterface.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

public class StatisticsServiceImplTest {
    public List<Double> doubleList = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
    public double expectedMean = 3.0;
    public double expectedStandardDeviation = 1.581;
    public double expectedVariance = 2.5;

    @Test
    public void calculateMean() throws Exception {
        StatisticsServiceImpl service = new StatisticsServiceImpl();

        Request request = createRequest(doubleList);

        Response response = null;
        try {
            response = service.calculateMean(request);
        } catch (Fault e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(expectedMean, response.getResult(), 0.001);
    }

    @Test
    public void calculateVariance() throws Exception {
        StatisticsServiceImpl service = new StatisticsServiceImpl();

        Request request = createRequest(doubleList);

        Response response = null;
        try {
            response = service.calculateVariance(request);
        } catch (Fault e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(expectedVariance, response.getResult(), 0.001);
    }

    @Test
    public void calculateStandardDeviation() throws Exception {
        StatisticsServiceImpl service = new StatisticsServiceImpl();

        Request request = createRequest(doubleList);

        Response response = null;
        try {
            response = service.calculateStandardDeviation(request);
        } catch (Fault e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(expectedStandardDeviation, response.getResult(), 0.001);
    }

    @Test
    public void throwFaultTest() throws Exception {
        StatisticsServiceImpl service = new StatisticsServiceImpl();

        Request request = createRequest(Arrays.asList(Double.NaN));

        try {
            service.calculateStandardDeviation(request);
            fail();
        } catch (Fault e) { }
    }

    @Test
    public void integrationTest() throws Exception {
        calculateMean();
        calculateVariance();
        calculateStandardDeviation();

        checkLastResult(expectedStandardDeviation);
    }

    private Request createRequest(List<Double> doubleList) {
        Request request = new ObjectFactory().createRequest();

        List<Double> newDoubleList = request.getDoubleList();
        newDoubleList.addAll(doubleList);

        return request;
    }

    private void checkLastResult(double expected) throws Exception {
        StatisticsServiceImpl service = new StatisticsServiceImpl();

        double lastResult = service.getLastResult(new ObjectFactory().createEmpty()).getResult();
        assertEquals(expected, lastResult, 0.001);
    }
}
