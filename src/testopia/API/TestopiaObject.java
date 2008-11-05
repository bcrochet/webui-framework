package testopia.API;

import java.util.Arrays;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

public abstract class TestopiaObject {

	protected Session session;
	protected String listMethod;
	
	protected Object callXmlrpcMethod(String methodName, Object... params) throws XmlRpcException{	
		return (Object) session.getClient().execute(methodName, Arrays.asList(params));	
	}

	/**
	 * Generic method designed to obtain a list of objects that match parameters
	 * supplies in provided HashMap object
	 * @param values a Map with the parameters that will be searched for;
	 * if you supply the pair {"plan_id": 5}, plan_id #5 will be returned. Any combination
	 * of attributes can be entered and the result will be all matches that fit 
	 * the input values
	 * @return list of matching objects
	 */
	public Object[] getList(Map<String, Object> values) throws XmlRpcException
	{
		//some Testopia objects have no listing mechanism
		if(listMethod == null)
			return null;
		
		Object[] result = (Object[]) this.callXmlrpcMethod(listMethod, values);
		return result;
	}
}
