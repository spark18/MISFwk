package com.shizhong.solr;

import java.util.HashMap;
import java.util.Map;
import static com.shizhong.web.bizmodel.EntityType.*;

public class SolrQueryConstructorRepo {
    private static final SolrQueryConstructorRepo repo = new SolrQueryConstructorRepo();
    private SolrQueryConstructorRepo(){
        constructors.put(TingZiNanHui.name(), TingZiNanHuiSolrUtil.getInstance());
        constructors.put(YangZhaoDian.name(), YangZhaoDianSolrUtil.getInstance());
    }
    
    public static final SolrQueryConstructorRepo getRepo() {
        return repo;
    }
    private Map<String, SolrQueryConstructor> constructors = new HashMap<String, SolrQueryConstructor>();
    
    public final SolrQueryConstructor getConstructor(String entity) {
        return constructors.get(entity);
    }
}
