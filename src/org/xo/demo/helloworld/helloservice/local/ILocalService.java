package org.xo.demo.helloworld.helloservice.local;

public interface ILocalService {

    int getPid();

    int getUid();

    int getTid();

    String getCaller();

    void setCaller(String caller);
}
