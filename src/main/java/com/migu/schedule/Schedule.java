package com.migu.schedule;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

/*
*类名和方法不能修改
 */
public class Schedule {

	private List<Integer> servers;
	
	private List<Map<Integer,Integer>> taskRecord;
	
	private Map<Integer,Integer> taskMap;
	
	private List<TaskInfo> taskinfo;
	
	private List<Integer> tasksort;

    public int init() {
    	servers = new LinkedList<Integer>();
    	taskRecord = new LinkedList<Map<Integer,Integer>>();
    	taskMap = new HashMap<Integer,Integer>();
    	taskinfo = new LinkedList<TaskInfo>();
    	tasksort = new LinkedList<Integer>();
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
    	if(null == servers) servers = new LinkedList<Integer>();
    	if(-1 == nodeId)
    	{
    		return ReturnCodeKeys.E004;
    	}
    	for(Integer server:servers)
    	{
    		if(server == Integer.valueOf(nodeId))
    		{
    			return ReturnCodeKeys.E005;
    		}
    	}
    	servers.add(Integer.valueOf(nodeId));
    	System.out.println(servers.toString());
        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId) {
    	
    	for(Integer server:servers)
    	{
    		if(server == Integer.valueOf(nodeId))
    		{
    			servers.remove(Integer.valueOf(nodeId));
    			return ReturnCodeKeys.E006;
    		}
    	}
    	return ReturnCodeKeys.E007;
    }


    public int addTask(int taskId, int consumption) {

    	if(taskId <= 0)
    	{
    		return ReturnCodeKeys.E009;
    	}
    	if(!taskRecord.isEmpty()){
    		for(Map<Integer,Integer> tempMap : taskRecord){
        		for (Map.Entry<Integer, Integer> entry : tempMap.entrySet()) {  
        			Integer key = entry.getKey();  
        			Integer value = entry.getValue();  
        			if(Integer.valueOf(taskId) == key)
        			{
        				return ReturnCodeKeys.E010;
        			}
         		   System.out.println("key=" + key + " value=" + value);  
         		 }  
        	}
    	}
    	taskMap.put(taskId, consumption);
    	taskRecord.add(taskMap);
        return ReturnCodeKeys.E008;
    }


    public int deleteTask(int taskId) {
    	
    	if(taskId <= 0)
    	{
    		return ReturnCodeKeys.E009;
    	}
    	
    	if(!taskRecord.isEmpty()){
    		for(Map<Integer,Integer> tempMap : taskRecord){
        		for (Map.Entry<Integer, Integer> entry : tempMap.entrySet()) {  
        			Integer key = entry.getKey();  
        			if(Integer.valueOf(taskId) == key)
        			{
        				taskRecord.remove(entry);
        				return ReturnCodeKeys.E011;
        			}
         		 }  
        	}
    	}
    	if(!taskinfo.isEmpty()){
    		for(TaskInfo tasktmp : taskinfo)
    		{
    			if(Integer.valueOf(taskId) == tasktmp.getTaskId())
    			{
    				taskinfo.remove(tasktmp);
    				return ReturnCodeKeys.E011;
    			}
    		}
    	}
    	return ReturnCodeKeys.E012;
    }


    public int scheduleTask(int threshold) {
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
    	if(null == tasks)
    	{
    		return ReturnCodeKeys.E016;
    	}
    	tasksort = new LinkedList<Integer>();
    	for(TaskInfo tasktmp : tasks){
    		if (false == taskStaus(tasktmp))
    		{
    			tasksort.add(tasktmp.getTaskId());
    		}
    	}
        return ReturnCodeKeys.E015;
    }
    
    boolean taskStaus(TaskInfo task){
    	for(Map<Integer,Integer> tempMap : taskRecord){
    		for (Map.Entry<Integer, Integer> entry : tempMap.entrySet()) {  
    			Integer key = entry.getKey();  
    			if(Integer.valueOf(task.getTaskId()) == key)
    			{
    				tasksort.add(-1);
    				return true;
    			}
     		 }  
    	}
    	return false;
    }
}
