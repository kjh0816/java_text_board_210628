package com.kjh.exam.app.interceptor;

import com.kjh.exam.app.Rq;

public abstract class Interceptor {
	
	public abstract boolean run(Rq rq);
}
