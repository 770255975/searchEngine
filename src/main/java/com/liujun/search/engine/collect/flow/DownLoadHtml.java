package com.liujun.search.engine.collect.flow;

import com.liujun.search.common.flow.FlowServiceContext;
import com.liujun.search.common.flow.FlowServiceInf;
import com.liujun.search.element.download.DownLoad;
import com.liujun.search.engine.collect.constant.CollectFlowKeyEnum;
import com.liujun.search.engine.collect.constant.WebEntryEnum;
import com.liujun.search.engine.collect.operation.filequeue.FileQueue;

/**
 * 下载html网页
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/17
 */
public class DownLoadHtml implements FlowServiceInf {

  /** 实例对象 */
  public static final DownLoadHtml INSTANCE = new DownLoadHtml();

  @Override
  public boolean runFlow(FlowServiceContext context) throws Exception {

    // 获取入口
    WebEntryEnum entry = context.getObject(CollectFlowKeyEnum.WEB_ENTRY.getKey());

    // 1,从文件队列中获取地址信息
    String urlAddress = FileQueue.GetQueue(entry).get();

    // 进行下载文件的操作
    String htmlContext = DownLoad.INSTANCE.downloadHtml(urlAddress);

    context.put(CollectFlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey(), urlAddress);
    context.put(CollectFlowKeyEnum.FLOW_DOWNLOAD_CONTEXT.getKey(), htmlContext);

    return true;
  }
}
