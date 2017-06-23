package nl.hu.iac.ws;

import javax.jws.WebService;

import com.sun.xml.ws.developer.SchemaValidation;

import nl.hu.iac.wsinterface.*;

@WebService(endpointInterface = "nl.hu.iac.wsinterface.StatisticsServiceInterface")
@SchemaValidation(handler = SchemaValidationErrorHandler.class)
public class StatisticsServiceImpl implements StatisticsServiceInterface {
    @Override
    public Response calculateMean(Request request) throws Fault {
        ObjectFactory factory = new ObjectFactory();
        Response response = factory.createResponse();

        Double result = Statistics.getMean(request.getDoubleList());

        checkResult(result, factory);
        response.setResult(result);

        return response;
    }

    @Override
    public Response calculateVariance(Request request) throws Fault {
        ObjectFactory factory = new ObjectFactory();
        Response response = factory.createResponse();

        Double result = Statistics.getVariance(request.getDoubleList());

        checkResult(result, factory);
        response.setResult(result);

        return response;
    }

	@Override
	public Response calculateStandardDeviation(Request request) throws Fault {
		ObjectFactory factory = new ObjectFactory();
		Response response = factory.createResponse();

        Double result = Statistics.getStandardDeviation(request.getDoubleList());

        checkResult(result, factory);
        response.setResult(result);

		return response;
	}

    @Override
    public Response getLastResult(Empty request) throws Fault {
        ObjectFactory factory = new ObjectFactory();
        Response response = factory.createResponse();

        Double result = Statistics.getLastResult();

        response.setResult(result);

        return response;
    }

    public void checkResult(Double result, ObjectFactory factory) throws Fault {
        if (result.isNaN() || result.isInfinite()) {
            StatisticsFault x = factory.createStatisticsFault();
            x.setErrorCode((short) 1);
            x.setMessage("Resultaat is NaN of INF.");
            Fault fault = new Fault("Er ging iets mis met het berekenen van de standaarddeviatie", x);
            throw fault;
        }
    }
}
