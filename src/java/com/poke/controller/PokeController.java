/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poke.controller;

import com.poke.model.PokeDaoImp;
import com.poke.model.PokeModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author USER
 */
@Controller
public class PokeController {
    
    @Autowired
    PokeDaoImp pokeDao;
    
    @RequestMapping("/home")
    public String index() {
        return "home";
    }
    
    @RequestMapping("/pokeview")
    public ModelAndView view() {
        List<PokeModel> pokelist = pokeDao.getAllPokeData();
        return new ModelAndView("pokeview", "pokedata", pokelist);
    }
    
    @RequestMapping(value="/search", method=RequestMethod.POST)
    public ModelAndView search(WebRequest request) {
        String name = request.getParameter("name");
        List<PokeModel> pokelist = new ArrayList<PokeModel>();
        pokelist.add(pokeDao.getPokeSearch(name));
        return new ModelAndView("pokeview", "pokedata", pokelist);
    }
    
    @RequestMapping(value="/detail/{name} ")
    public ModelAndView detail(@PathVariable String name) {
        List<PokeModel> pokelist = new ArrayList<PokeModel>();
        pokeDao.getPokeSearch(name);        
        pokelist.add(pokeDao.getPokeSearch(name));
        return new ModelAndView("pokedetail", "pokedata", pokelist);
    }
}
