package in.ashokit.saiteja1.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import in.ashokit.saiteja1.entity.Plan;
@Repository
public interface PlanService 
{
	public Map<Integer,String> getPlanCateogry();
	public boolean savePlan(Plan plan);
	public List<Plan> getAllPlans();
	public Plan getPlanById(Integer planId);
	public boolean deletePlanById(Integer planId);
	public boolean updatePlan(Plan plan);
	public boolean planChangeStatus(Integer planId,String status);
}
