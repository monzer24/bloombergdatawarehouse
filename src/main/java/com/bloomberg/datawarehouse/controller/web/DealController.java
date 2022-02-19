package com.bloomberg.datawarehouse.controller.web;

import com.bloomberg.datawarehouse.controller.form.DealForm;
import com.bloomberg.datawarehouse.dto.DealDTO;
import com.bloomberg.datawarehouse.exception.DealException;
import com.bloomberg.datawarehouse.facade.DealFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/***
 * @author monzer
 * The endpoint conntrollers to add, view, and view all deals
 */
@Controller
@RequestMapping(value = "/")
public class DealController {

    private static final Logger LOG = LoggerFactory.getLogger(DealController.class);

    @Resource(name = "dealFacade")
    private DealFacade dealFacade;

    @Resource(name = "dealFormValidator")
    private Validator dealValidator;

    @GetMapping(value = {"/"})
    public String getAddDealView(Model model){
        model.addAttribute("deal", new DealForm());
        LOG.info("Viewing the add deals page (homepage)");
        return "addDeal";
    }

    @PostMapping(value =  "/")
    public String addDeal(@ModelAttribute("deal") DealForm form, Model model, BindingResult result){
        getDealValidator().validate(form, result);
        if(result.hasErrors()){
            LOG.error("Errors was found from the UI when adding a deal");
            model.addAttribute("deal", form);
            model.addAttribute("errorMsg", "Errors on submitted deal, please check the fields below.");
            return "addDeal";
        }
        DealDTO dealDTO = populateFormIntoDTO(form);
        DealDTO newDeal;
        try {
            newDeal= getDealFacade().createNewDeal(dealDTO);
        } catch (DealException e) {
            LOG.error("Errors was found from the UI when adding a deal: {}", e.getMessage());
            model.addAttribute("errorMsg", e.getMessage());
            return "redirect:/deals";
        }

        if(newDeal == null){
            LOG.error("Deal has not been added successfully");
            return "redirect:/deals";
        }
        return  "redirect:/"+newDeal.getDealCode();
    }

    @GetMapping(value = "/{dealId}")
    public String viewDeal(@PathVariable("dealId")String dealId, Model model){
        LOG.info("Viewing the Deal {}", dealId);
        Optional<DealDTO> dealById = getDealFacade().getDealById(dealId);
        if(dealById.isEmpty()){
            LOG.error("Deal {} was not found", dealId);
            return "redirect:/";
        }
        model.addAttribute("deal", dealById.get());
        return "dealInfo";
    }

    @GetMapping("/deals")
    public String viewAllDeals(Model model){
        List<DealDTO> allDeals = getDealFacade().getAllDeals();
        LOG.info("{} deals was found", allDeals.size());
        model.addAttribute("deals", allDeals);
        return "allDeals";
    }

    // converting the request parameters into a DTO
    private DealDTO populateFormIntoDTO(DealForm form){
        DealDTO dto = new DealDTO();
        if(form != null){
            dto.setDealCode(form.getDealId());
            dto.setDealDate(LocalDateTime.parse(form.getDealDate()));
            dto.setDealAmount(BigDecimal.valueOf(form.getDealAmount()));
            dto.setOrderCurrency(Currency.getInstance(form.getOrderCurrencyIsoCode()));
            dto.setToCurrency(Currency.getInstance(form.getToCurrencyIsoCode()));
        }
        return dto;
    }

    public DealFacade getDealFacade() {
        return dealFacade;
    }

    public Validator getDealValidator() {
        return dealValidator;
    }

    @ModelAttribute("supportedCurrencies")
    public List<Currency> getCurrencies(){
        return Currency.getAvailableCurrencies().stream().sorted(Comparator.comparing(Currency::getCurrencyCode)).collect(Collectors.toList());
    }

}
