package com.cyta.inbounds;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.annotations.cache.NoCache;


@Path("/test")
public class RestResource{


	@GET
	@NoCache
	@Counted(name = "performedChecks", description = "How many the restResource method have been Called.")
	@Timed(name = "testRestTimer", description = "testRestTimer.", unit = MetricUnits.MILLISECONDS)
	@Operation(summary = "test request",description = "test request")
	@APIResponse( name = "helloResponse", responseCode = "200", description = "hello")
	public String hello() {
		return "HELLO ";
	}
}
