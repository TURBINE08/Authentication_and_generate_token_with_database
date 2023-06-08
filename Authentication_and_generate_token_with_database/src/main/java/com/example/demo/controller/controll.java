package com.example.demo.controller;

import java.beans.Encoder;
import java.security.SecureRandom;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
//import java.util.Array; 
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Userdetail;
import com.example.demo.payloads.JwtRequest;
import com.example.demo.payloads.JwtResponse;
import com.example.demo.payloads.Login;
import com.example.demo.payloads.LoginMbNu;
//import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.UserDto;
import com.example.demo.serviceImpl.JwtCustomUserDetailsService;
import com.example.demo.serviceImpl.Serviceeimpl;
import com.example.demo.util.Jwtutil;

@RestController
//@RequestMapping("/api/users")
public class controll {
	@Autowired
	private Serviceeimpl serviceeimpl;
	@Autowired
	private JwtController jwtController;
	@Autowired
	private JwtRequest jwtRequest;
	@Autowired
	private Userdetail userdetail;
	@Autowired
	private RestTemplate restTemplate;

//*************************************************************************************************	

//	@Autowired
//	private Jwtutil jwtUtil;
//	
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	
//	@Autowired
//	private  JwtCustomUserDetailsService customUserDetailsService;
////	@RequestMapping(value = "/token", method = RequestMethod.POST)
//	@PostMapping("/token")
//	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
//	{
//		System.err.println("*********");
//		System.out.println(jwtRequest);
//		try
//		{
//			System.err.println("*********");
//			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
//		}
//		catch(UsernameNotFoundException e)
//		{
//			e.printStackTrace();
//			throw new Exception("Bad Credential");
//		}
//		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
//		String token = this.jwtUtil.generateToken(userDetails);
//		System.err.println("JWT " + token );
//		return ResponseEntity.ok(new JwtResponse(token));// it convert json
//		
//	}

//*************************************************************************************************	
	@RequestMapping("/welcome")
	public String welcome() {
		String text = "this token generated string";

		Base64.Encoder encoder = Base64.getEncoder();
		String encoded = encoder.encodeToString(text.getBytes());
//		System.err.println("encoder form   " + encoded);

		Base64.Decoder decoder = Base64.getDecoder();
		byte[] arr = decoder.decode(encoded.getBytes());
//		String str = new String(arr);

//		String st  r = arr.toString();
		String str = String.valueOf(arr);
		System.err.println("decoder form   " + str);

		return text;
	}

//*************************************************************************************************	
	@PostMapping("/postt")
	public ResponseEntity<UserDto> create(@RequestBody UserDto userdto) {
//		System.err.println(userdto.getMbNumber());
		System.err.println(userdto.getPassword());

		Base64.Encoder encoder = Base64.getEncoder();
		String encoded = encoder.encodeToString(userdto.getPassword().getBytes());
		System.err.println("encoder form   " + encoded);
		userdto.setPassword(encoded);

		UserDto ud = serviceeimpl.createUser(userdto);
		return new ResponseEntity<>(ud, HttpStatus.CREATED);
	}

//*************************************************************************************************	
	@PostMapping("/update")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userdto, @PathVariable Integer Id) {
		UserDto usd = serviceeimpl.updateUser(userdto);

		return new ResponseEntity<>(usd, HttpStatus.OK);
	}

//*************************************************************************************************	
	@GetMapping("/getall")
	public ResponseEntity<List<UserDto>> getAll() {
		List<UserDto> lst = serviceeimpl.getAll();

		return new ResponseEntity<>(lst, HttpStatus.ACCEPTED);
	}

//*************************************************************************************************	

	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<?> dltbyId(@PathVariable("id") Integer uid) {
		Map<String, String> map = new LinkedHashMap<>();
		try {
			serviceeimpl.deleteById(uid);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			map.put("status", "fail");
			map.put("message", "id not exists");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

//		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);

//		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted successfully","done",true),HttpStatus.OK);
	}

//*************************************************************************************************	

	@GetMapping("/getById/{Id}")
	public ResponseEntity<List<UserDto>> getByIde(@PathVariable("Id") Integer id) {
		List<UserDto> udto = serviceeimpl.getById(id);

		return new ResponseEntity<>(udto, HttpStatus.OK);
	}

//*************************************************************************************************	

	@PutMapping("/PutUpdate/{Id}")
	public ResponseEntity<UserDto> PostUser(@RequestBody UserDto userdto, @PathVariable("Id") Integer id) {
		UserDto rtnn = serviceeimpl.PostUser(userdto, id);
//		String statu = id+"nu d is updated";
		return new ResponseEntity<>(rtnn, HttpStatus.OK);
	}

//*************************************************************************************************	

	Integer id;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Login logins) throws Exception {

		Base64.Encoder encoder = Base64.getEncoder();
		byte[] arr = logins.getPassword().getBytes();
		
		String encoded = encoder.encodeToString(arr);

//		String encoded = encoder.encodeToString(logins.getPassword().getBytes());

		logins.setPassword(encoded);

		Map<String, String> map = new LinkedHashMap<>();
		Boolean rtnn = serviceeimpl.login(logins.getEmail(), logins.getPassword());
		if (rtnn == true) {
			System.err.println("rtnn == true");
			
			String token = jwtController.generateToken(jwtRequest);
			//sonambewfa_hai  = password of sonam gupta
			map.put("login status", " Successfully");
			map.put("massage", "token generated successfully");
			map.put("token", token);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			System.err.println("rtnn == false");
			map.put("login", "sorry login Un-successfully");
			map.put("massage", "token is not generated");
			map.put("token", "null");
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}

	}

//*************************************************************************************************	

	@GetMapping("/getdetail")
	public ResponseEntity<List<UserDto>> getByIde() {
		System.out.println(userdetail.getId());
		List<UserDto> udto = serviceeimpl.getById(id);

		return new ResponseEntity<>(udto, HttpStatus.OK);
	}

//*************************************************************************************************	

//	@GetMapping("/otp")
	public String getotp() {
		Random random = new SecureRandom();
		String otp = "7887405442";
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			builder.append(otp.charAt(random.nextInt(otp.length())));
		}
		String ottp = builder.toString();
		System.err.println(ottp);
		return ottp;
	}

//*************************************************************************************************	

	@PostMapping("/loginNumber")
	public ResponseEntity<?> LoginWithNumber(@RequestBody LoginMbNu nu) throws Exception {
		String number = nu.getNumber();
		Userdetail rtn = serviceeimpl.getByMbNumber(number);
		if (rtn == null) {
			System.err.println("sorry");
			return new ResponseEntity<>("not Successfully", HttpStatus.BAD_GATEWAY);
		} else {
			Random random = new Random();
			String ottp = String.format("%04d", random.nextInt(10000));
			rtn.setOtp(ottp);
			Userdetail userdetail = serviceeimpl.uppdateUser(rtn);
			return new ResponseEntity<>(userdetail, HttpStatus.ACCEPTED);
		}
	}

//*************************************************************************************************	

	@PostMapping("/loginByNumberOtp")
	public ResponseEntity<?> loginByNumberOtp(@RequestBody Login logins) throws Exception {

		Map<String, String> map = new LinkedHashMap<>();
		Boolean rtnn = serviceeimpl.existsByMbNumberAndOtp(logins.getMbNumber(), logins.getOtp());
		if (rtnn == true) {
			System.err.println("rtnn == true");
			String token = jwtController.generateToken(jwtRequest);

//		ArrayList<?> str = restTemplate.getForObject("http://localhost:9092/api/_getAllCandidateDetails",ArrayList.class );

			map.put("login status", " Successful");
			map.put("massage", "token generated successfully");
			map.put("token", token);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			System.err.println("rtnn == false");
			map.put("login", "sorry login Un-successful");
			map.put("massage", "token is not generated");
			map.put("token", "null");
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}
	}

}
