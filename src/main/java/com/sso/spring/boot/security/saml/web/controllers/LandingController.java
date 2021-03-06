/*
 * Copyright 2019 Vincenzo De Notaris
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package com.sso.spring.boot.security.saml.web.controllers;

import com.google.gson.Gson;
import com.sso.spring.boot.security.saml.web.user.repository.GlobalRolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sso.spring.boot.security.saml.web.stereotypes.CurrentUser;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LandingController {
	
	// Logger
	private static final Logger LOG = LoggerFactory
			.getLogger(LandingController.class);
@Autowired
	GlobalRolesRepository globalRolesRepository;
	@RequestMapping("/landing")
	public String landing(@CurrentUser User user, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null)
			LOG.debug("Current authentication instance from security context is null");
		else
			LOG.debug("Current authentication instance from security context: "
					+ this.getClass().getSimpleName());
		globalRolesRepository.findAll().forEach(s->
				LOG.info("roles :"+s)
				);
		model.addAttribute("username", 	user.getUsername());
		model.addAttribute("sample","jwt");
		return new Gson().toJson(user);
	}

}
