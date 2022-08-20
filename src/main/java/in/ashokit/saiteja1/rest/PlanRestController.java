package in.ashokit.saiteja1.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.saiteja1.entity.Plan;
import in.ashokit.saiteja1.service.PlanServiceImp;

@RestController
public class PlanRestController {
	@Autowired
	private PlanServiceImp planServiceImp;
	
	@GetMapping("/cateogries")
	public ResponseEntity<Map<Integer,String>> planCateogry(){
		  Map<Integer,String> categories = planServiceImp.getPlanCateogry();
		  return new ResponseEntity<Map<Integer,String>>(categories,HttpStatus.OK);
	}
	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan){
		boolean savePlan = planServiceImp.savePlan(plan);
		String msg="";
		if(savePlan) {
			msg="Planed is saved sucessful.....!!!!";
		}
		else {
			msg = "Plan is not saved.....";
		}
		return new ResponseEntity(msg,HttpStatus.CREATED);
		
	}
	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> allPlans(){
		List<Plan> allPlans = planServiceImp.getAllPlans();
		return new ResponseEntity<List<Plan>>(allPlans,HttpStatus.OK);
	}
	@GetMapping("planId/{planId}")
	public ResponseEntity<String> editPlan(@PathVariable Integer planId){
		Plan plan = planServiceImp.getPlanById(planId);
		return new ResponseEntity(plan,HttpStatus.OK);
	}
	@PutMapping("/updatePlan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
		boolean isUpdated = planServiceImp.updatePlan(plan);
		String msg= "";
		if(isUpdated)
		{
			msg="Plan is Updated sucessfully";
		}
		else {
			msg = "Plan is not Updated ";
		}
		
		return new ResponseEntity(msg,HttpStatus.OK);
	}
	@DeleteMapping("/deletePlan/{planId}")
	public ResponseEntity<String> deletePlan( @PathVariable Integer planId){
		boolean isDeleted = planServiceImp.deletePlanById(planId);
		String msg= "";
		if(isDeleted)
		{
			msg="Plan is Deleted sucessfully";
		}
		else {
			msg = "Plan is not Deleted ";
		}
		
		return new ResponseEntity(msg,HttpStatus.OK);
		
	}
	
	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId,@PathVariable  String status){
		 boolean isStatusChanged = planServiceImp.planChangeStatus(planId, status);
		String msg ="";
		if(isStatusChanged) {
			msg="Status Changed Sucessfully ..!!!!!!";
			}
		else {
			msg = "Status not Changed......";
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
		}
}
