package in.ashokit.saiteja1.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.saiteja1.entity.Plan;
import in.ashokit.saiteja1.entity.PlanCateogry;
import in.ashokit.saiteja1.repo.PlanCateogryReop;
import in.ashokit.saiteja1.repo.PlanReop;

@Service
public class PlanServiceImp implements PlanService{
	@Autowired
	private PlanReop planRepo;
	@Autowired
	private PlanCateogryReop planCateogryReop;
	@Override
	public Map<Integer, String> getPlanCateogry() {
		List<PlanCateogry> catogories = planCateogryReop.findAll();
		Map<Integer,String> cateogryMap = new HashMap<Integer,String>();
		catogories.forEach(category->{
			cateogryMap.put(category.getCategoryId(), category.getCategoryName());
		
		});
		
		return cateogryMap;
	}
	@Override
	public boolean savePlan(Plan plan) {
		Plan saved = planRepo.save(plan);
	//return saved.getPlanId()!=null ? true : false;(using the terniary operator)
		return saved.getPlanId()!=null;
	}
	@Override
	public List<Plan> getAllPlans() {
		return  planRepo.findAll();
		
	}
	@SuppressWarnings("deprecation")
	@Override
	public Plan getPlanById(Integer planId) {
		Optional<Plan> planById = planRepo.findById(planId);
		if(planById.isPresent())
		{
			return planById.get();
		}
		return null;
	}
	@Override
	public boolean deletePlanById(Integer planId) {
		boolean status=false;
		try {
			planRepo.deleteById(planId);
			status = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updatePlan(Plan plan) {
		Plan save = planRepo.save(plan);// upsert : insetion(if the primary key nnot avaliable + updation(if the primary key  avaliable)
		 return plan.getPlanId()!=null;
	}
	@Override
	public boolean planChangeStatus(Integer planId, String status) {
		Optional<Plan> findById = planRepo.findById(planId);
		if(findById.isPresent()) {
			Plan plan = findById.get();
			plan.setActiveSw(status);
			planRepo.save(plan);
			return true;
			
		}
		return false;
	}
	
	
}
