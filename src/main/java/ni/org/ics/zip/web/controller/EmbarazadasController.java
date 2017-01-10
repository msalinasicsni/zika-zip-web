package ni.org.ics.zip.web.controller;

import ni.org.ics.zip.service.EmbarazadaService;
import ni.org.ics.zip.utils.ZpDetalleEventoPanelControl;
import ni.org.ics.zip.utils.ZpPanelControlEmbarazada;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador web de peticiones relacionadas a embarazadas
 * 
 * @author William Avil�s
 */
@Controller
@RequestMapping("/pregnants/*")
public class EmbarazadasController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmbarazadasController.class);

    @Resource(name="embarazadaService")
    private EmbarazadaService embarazadaService;

    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String home(Model model) {
    	logger.info("Pagina principal embarazadas");
        List<ZpPanelControlEmbarazada> estadoEmbarazadas = new ArrayList<ZpPanelControlEmbarazada>();
        try {
            estadoEmbarazadas = embarazadaService.getZpPanelControlEmbarazadas();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("registros", estadoEmbarazadas);
        return "pregnants/dashboard";
    }

    @RequestMapping(value = "dashboard/{code}/{event}", method = RequestMethod.GET)
    public String initUpdateUserForm(@PathVariable("code") String code, @PathVariable("event") String event, Model model) {
        logger.info("obtener detalle de evento embarazada");
        ZpDetalleEventoPanelControl zpDetalleEventoPanelControl = new ZpDetalleEventoPanelControl();
        zpDetalleEventoPanelControl.setCodigo(code);
        zpDetalleEventoPanelControl.setEvento(event);
        try {
             zpDetalleEventoPanelControl = embarazadaService.getZpDetalleEventoPanelControl(zpDetalleEventoPanelControl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("detalle", zpDetalleEventoPanelControl);
        return "pregnants/dashboardDetail";
    }

	
}
