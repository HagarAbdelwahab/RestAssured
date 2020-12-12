package com.cyta;

import javax.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@OpenAPIDefinition (
		info = @Info(
				title = "FS Archetype",
				version = "0.1",
				description = "FS Archetype")
)
public class ApplicationService extends Application { }
