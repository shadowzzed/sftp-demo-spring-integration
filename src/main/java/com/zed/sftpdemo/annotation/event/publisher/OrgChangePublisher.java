package com.zed.sftpdemo.annotation.event.publisher;

import com.zed.sftpdemo.annotation.event.model.OrgChangeModel;
import com.zed.sftpdemo.annotation.event.source.OrgChangesSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author Zeluo
 * @date 2019/9/20 9:14
 */
//@EnableBinding(OrgChangesSource.class)
public class OrgChangePublisher {
    @Autowired
    private OrgChangesSource source;

    public void publish(String action, String orgId) {
        OrgChangeModel model = new OrgChangeModel(
                OrgChangeModel.class.getTypeName(),
                action,
                orgId,
                null
        );
        source.output().send(MessageBuilder.withPayload(model).build());
    }
}
