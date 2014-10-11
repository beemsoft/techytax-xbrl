package org.techytax.service;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.techytax.domain.Project;

@XmlRootElement
public class ProjectList {
	
	@XmlElement
	public List<Project> projects;

}
