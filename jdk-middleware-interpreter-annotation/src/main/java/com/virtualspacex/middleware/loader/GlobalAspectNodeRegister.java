package com.virtualspacex.middleware.loader;

import java.util.HashSet;
import java.util.Set;

import com.virtualspacex.middleware.aspect.AspectNode;
import com.virtualspacex.middleware.exception.InterpreAnnotationException;

public class GlobalAspectNodeRegister {
    private static Set<Class<? extends AspectNode>> globalAspectList = new HashSet<>();

    public synchronized static void register(Class<? extends AspectNode> clazz){
        if(null != clazz){
            globalAspectList.add(clazz);
        }
    }

    public synchronized static AspectNode getGlobalAspectNode() throws InterpreAnnotationException {

        AspectNode aspectNode = null;

        if(0 == globalAspectList.size()){
            return aspectNode;
        }

        //生成默认切面节点
        for(Class<? extends AspectNode> aspectNodeClazz : globalAspectList){
            try {
                AspectNode node = aspectNodeClazz.newInstance();
                node.setNext(aspectNode);
                aspectNode = node;
            } catch (Exception e){
                throw new InterpreAnnotationException(e);
            }

        }

        return aspectNode;
    }
}   