package uhk.sakac.recenzentiuhk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uhk.sakac.recenzentiuhk.model.Actor;
import org.springframework.ui.Model;
import uhk.sakac.recenzentiuhk.service.implementation.ActorServiceImplementation;
import uhk.sakac.recenzentiuhk.utility.FileUpload;
import uhk.sakac.recenzentiuhk.utility.ImageType;

@Controller
@RequestMapping("/admin/actors")
public class ActorController {

    @Autowired
    private ActorServiceImplementation actorService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("actors", actorService.getActors());
        return "admin/movie/actor/actors";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createActorForm(Model m) {
        m.addAttribute("actorForm", new Actor());
        return "admin/movie/actor/actor_form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createActor(Actor actor, BindingResult result, @RequestParam(value = "file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(actor);
            return "admin/movie/actor/actor_form";
        }
        try {
            String path = FileUpload.saveImage(ImageType.CAST_DP, actor.getActorName(), file);
            actor.setImageUrl(path);
            actorService.saveActor(actor);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(actor);
            return "admin/movie/actor/actor_form";
        }
        return "redirect:/admin/actors/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String updateActorForm(@PathVariable("id") long id, Model model) {
        try {
            Actor actor = actorService.getActorById(id);
            model.addAttribute("actorForm", actor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/movie/actor/actor_form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateActor(@PathVariable("id") long id, @Validated Actor actor, BindingResult result, Model model,
                              @RequestParam(value = "file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            actor.setId(id);
            redirectAttributes.addFlashAttribute(actor);
            return "admin/movie/actor/actor_form";
        }

        try {
            if (!file.isEmpty()){
                String path = FileUpload.saveImage(ImageType.CAST_DP, actor.getActorName(), file);
                actor.setImageUrl(path);
            }
            else {
                actor.setImageUrl(actorService.getActorById(id).getImageUrl());
            }
            actorService.saveActor(actor);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(actor);
            return "admin/movie/actor/actor_form";
        }
        return "redirect:/admin/actors/";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteActor(@PathVariable("id") long id, RedirectAttributes redirAttr) {
        try {
            Actor actor = actorService.getActorById(id);
            actorService.deleteActor(actor);
        } catch (Exception e) {
            redirAttr.addFlashAttribute("error", "Unable to delete cast in a movie.");
            return "redirect:/admin/actors/";
        }

        return "redirect:/admin/actors/";
    }
}

