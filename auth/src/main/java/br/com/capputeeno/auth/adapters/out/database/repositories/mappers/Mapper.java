package br.com.capputeeno.auth.adapters.out.database.repositories.mappers;


public abstract class Mapper<D, I> {

    abstract public I toInfra(D domain);

    abstract public D toDomain(I infra);

}
