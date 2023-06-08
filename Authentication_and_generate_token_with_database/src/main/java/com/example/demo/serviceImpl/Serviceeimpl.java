package com.example.demo.serviceImpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.catalina.User;
//import org.apache.tomcat.util.file.Matcher;
//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.Repo;
import com.example.demo.controller.controll;
import com.example.demo.entity.Userdetail;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payloads.UserDto;
import com.example.demo.service.Servicee;

//@Configuration
@Service
public class Serviceeimpl implements Servicee {
//	@Autowired
//	public ModelMapper modelMapper;
	@Autowired
	private Repo repo;
	

	@Override
	public UserDto createUser(UserDto userdto) {
		Userdetail userdetail = dtoToUser(userdto);
		System.err.println(userdetail.getMbNumber());
		System.err.println(userdetail.getEmail());
		Userdetail usrdtl = repo.save(userdetail);
		UserDto usrdtoo = UserTodto(usrdtl);

		return usrdtoo;
	} 

	@Override
	public UserDto updateUser(UserDto userdto) {
		Userdetail userdetail = dtoToUser(userdto);
		Userdetail usrdtl = repo.save(userdetail);
		UserDto usrdtoo = UserTodto(usrdtl);

		return usrdtoo;
	}

	@Override
	public List<UserDto> getById(Integer id) {
		Optional<Userdetail> usrdtl = repo.findById(id);
		List<UserDto> rtn = usrdtl.stream()
				.map(userdt -> UserTodto(userdt)).collect(Collectors.toList());

		return rtn;
	}

	@Override
	public List<UserDto> getAll() {
		List<Userdetail> rtn = repo.findAll();
		List<UserDto> rtnn = rtn.stream().map(userdt -> UserTodto(userdt)).collect(Collectors.toList());
		return rtnn;
	}

	@Override
	public void deleteById(Integer Id)
	{
		repo.deleteById(Id);

	}

	@Override
	public void deleteAll() 
	{
		repo.deleteAll();
	}

	@Override
	public UserDto PostUser(UserDto userdto, Integer id) {
		Userdetail usrdtl = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		usrdtl.setEmail(userdto.getEmail());
//		usrdtl.setId(userdto.getId()); 
		usrdtl.setFname(userdto.getFname());
		usrdtl.setLname(userdto.getLname());
		usrdtl.setPassword(userdto.getPassword());

		Userdetail usrdtll = repo.save(usrdtl);

		UserDto rtn = UserTodto(usrdtll);
		return rtn;
	}

	@Override
	public Boolean login(String Email, String Password) 
	{
		System.err.println("*****22*****");
//		int x  = '@'; 64
		System.err.println(Email);
		Pattern p = Pattern.compile("[@]");
		Matcher m = p.matcher(Email);
		Boolean x = m.find();
		if(x)
		{ 
		Boolean k = repo.existsByEmailAndPassword(Email, Password);
		return k;
		}
		else
		{
			Boolean k = repo.existsByFnameAndPassword(Email, Password);
			return k;	
		}		
	}
 
	
	
	@Override
	public Userdetail getByMbNumber(String MbNumber) 
	{
		Userdetail rtn = repo.findByMbNumber(MbNumber);
		return rtn;
	}
	
	
	@Override
	public Userdetail getByEmail(String email) 
	{
		Userdetail rtn = repo.findByEmail(email);
		System.out.println("return repo");
		return rtn;
	}
	
	public Optional<Userdetail> findById(Integer x)
	{
		Optional<Userdetail> rtn = repo.findById(x);
		return rtn;
	}
	
	
	
	@Override
	public Userdetail uppdateUser(Userdetail userdetail) {
		
		return repo.save(userdetail);
	}
	
	
	@Override
	public Boolean existsByMbNumberAndOtp(String MbNumber, String Otp) 
	{
		
		return repo.existsByMbNumberAndOtp(MbNumber, Otp);
	}
	
	
	
	
	
	

////	@Bean 
//	private Userdetail dtoToUser(UserDto userdto)
//	{
//		Userdetail userdetail = this.modelMapper.map(userdto, Userdetail.class);
//		return userdetail;
//	}
//	
////	@Bean
//	private UserDto UserTodto(Userdetail usrdtl)
//	{
//		UserDto usrdto = this.modelMapper.map(usrdtl, UserDto.class);
//		return usrdto;
//	}

	private Userdetail dtoToUser(UserDto userdto) {
		Userdetail userdtl = new Userdetail();
		userdtl.setId(userdto.getId());
		userdtl.setFname(userdto.getFname());
		userdtl.setLname(userdto.getLname());
		userdtl.setEmail(userdto.getEmail());
		userdtl.setPassword(userdto.getPassword());
		userdtl.setMbNumber(userdto.getMbNumber());
		userdtl.setOtp(userdto.getOtp());
		return userdtl;

	}

	private UserDto UserTodto(Userdetail usrdtl) {
		UserDto utd = new UserDto();
		utd.setId(usrdtl.getId());
		utd.setFname(usrdtl.getFname());
		utd.setLname(usrdtl.getLname());
		utd.setEmail(usrdtl.getEmail());
		utd.setPassword(usrdtl.getPassword());
		utd.setMbNumber(usrdtl.getMbNumber());
		utd.setOtp(usrdtl.getOtp());
		return utd;
	}

	

	

	

}
