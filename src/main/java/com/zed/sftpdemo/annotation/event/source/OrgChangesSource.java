package com.zed.sftpdemo.annotation.event.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author Zeluo
 * @date 2019/9/20 9:09
 */
public interface OrgChangesSource {
    @Output("orgChangeOutput")
    MessageChannel output();
}
