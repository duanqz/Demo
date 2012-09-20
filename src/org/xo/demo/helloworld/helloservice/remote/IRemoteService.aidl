package org.xo.demo.helloworld.helloservice.remote;

interface IRemoteService {

	int getPid();

	int getUid();

    int getTid();

	String getCaller();

	void setCaller(String caller);
}