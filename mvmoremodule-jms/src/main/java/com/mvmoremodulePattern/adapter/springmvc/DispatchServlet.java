package com.mvmoremodulePattern.adapter.springmvc;/**
 * Created by Administrator on 2018-06-18.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2018-06-18-17:18
 */
public class DispatchServlet {
    public static List<HandlerAdapter> handlerAdapterList = new ArrayList<HandlerAdapter>();

    public static void main(String agrs[]){
        DispatchServlet dispatchServlet = new DispatchServlet();
        dispatchServlet.doDispatch();
    }

    public void doDispatch(){
        SimpleController controller = new SimpleController();
        HandlerAdapter handlerAdapter = getHandler(controller);
        handlerAdapter.handle(controller);
    }

    public DispatchServlet(){
        handlerAdapterList.add(new AnnotationHandlerAdapter());
        handlerAdapterList.add(new HttpHandlerAdapter());
        handlerAdapterList.add(new SimpleHandlerAdapter());
    }



    public HandlerAdapter getHandler(Controller controller){
        for(HandlerAdapter handlerAdapter : this.handlerAdapterList){
            if(handlerAdapter.supports(controller)){
                return handlerAdapter;
            }
        }
        return null;
    }
}
