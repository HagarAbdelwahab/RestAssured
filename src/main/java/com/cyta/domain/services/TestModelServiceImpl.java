package com.cyta.domain.services;

import javax.enterprise.context.ApplicationScoped;
import com.cyta.domain.models.TestModel;
import io.vertx.core.cli.annotations.Name;


@ApplicationScoped
@Name("testService")
public class TestModelServiceImpl implements TestModelService {

	@Override
	public void test(TestModel model) { }
}
