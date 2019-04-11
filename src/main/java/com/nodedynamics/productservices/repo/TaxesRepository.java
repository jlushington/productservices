package com.nodedynamics.productservices.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.nodedynamics.productservices.model.product.TaxesModel;

public interface TaxesRepository extends MongoRepository<TaxesModel, String>{

}
