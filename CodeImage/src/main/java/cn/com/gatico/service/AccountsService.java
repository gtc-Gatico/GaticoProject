package cn.com.gatico.service;

import cn.com.gatico.index.Accounts;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AccountsService extends ElasticsearchRepository<Accounts, String>{


}