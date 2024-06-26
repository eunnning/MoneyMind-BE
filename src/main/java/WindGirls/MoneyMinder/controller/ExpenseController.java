package WindGirls.MoneyMinder.controller;

import WindGirls.MoneyMinder.domain.Account;
import WindGirls.MoneyMinder.dto.AccountDto;
import WindGirls.MoneyMinder.repository.AccountRepository;
import WindGirls.MoneyMinder.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000") // 프론트엔드 도메인으로 수정
@RestController
@RequestMapping("/api/account")
public class ExpenseController {

    private final AccountService accountService;
    private AccountRepository accountRepository;

    @Autowired
    public ExpenseController(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @GetMapping(value="/test" ,produces = "text/plain; charset=UTF-8")
    public String time()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "안녕하세요. 현재 서버의 시간은 " +sdf.format(new Date()) + " 입니다!";    }

    @GetMapping("/user/{user_id}")
    public List<Account> getAccountsByUserId(@PathVariable Long user_id) {
        System.out.println("컨트롤러 ---------------");
        return accountService.getAccountsByUserId(user_id);
    }

//    @GetMapping("/asset/{transaction_id}")
//    public List<Account> getAccountsByTransactionId(@PathVariable Long transaction_id) {
//        System.out.println("컨트롤러 ---------------");
//        return accountService.getAccountsByTransactionId(transaction_id);
//    }

        @PostMapping("/addExpense")
    public void addExpense() {
        accountService.addExpense();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        System.out.println("HelloController hello() " + new Date());

        return "hello"; // ajax를 통해 값을 front-end로 보내준다.
    }
    @GetMapping("/{date}/{userId}")
    public List<AccountDto> getAccountStatusByDateAndUser(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                                          @PathVariable Long userId) {
        System.out.println("Received request for date: " + date + "/" + userId);
        try {
            System.out.println("실행3");
            if (date != null) {
                // LocalDate를 Date로 변환
                System.out.println("널아님");
//                Date utilDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

                // 날짜를 원하는 형식으로 변환
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                String formattedDate = sdf.format(utilDate);
//                System.out.println("formattedDate: " + formattedDate);

                // 서비스에서 계정 상태 정보를 가져옴
                List<AccountDto> accountDtoList = accountService.getAccountStatusByDateAndUser(date, userId);
                System.out.println("-------------" + accountDtoList);
                return accountDtoList;
            } else {
                // date가 null인 경우의 처리
                return Collections.emptyList();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // 예외를 다시 던져서 외부로 전파
        }
    }
}
