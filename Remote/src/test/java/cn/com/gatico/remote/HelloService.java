package cn.com.gatico.remote;

import cn.com.gatico.remote.bean.User;
import org.springframework.remoting.support.RemoteInvocationExecutor;
import org.springframework.stereotype.Service;

@Service
public interface HelloService extends RemoteInvocationExecutor {
    public User getUser();
}
