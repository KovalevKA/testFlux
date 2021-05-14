import java.time.Duration;
import java.util.Date;
import reactor.core.publisher.Flux;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        //1. 10 секунд подряд выводить в консоль номер секунды
        Long count = 10L;

        Flux
            .generate(
                () -> new Date().getSeconds(),
                (date, sink) -> {
                    if (date == 60) {
                        date = 0;
                    }
                    sink.next(date++);
                    return date;
                }
            )
            .delayElements(Duration.ofSeconds(1L))
            .take(count)
            .subscribe(System.out::println);

        Thread.sleep((count + 1) * 1000);

        System.out.println("*********************");

        //2 Взять рандомный список дат и отфильтровать его

        Flux<Date> dateFlux = Flux
            .just(new Date(548512482), new Date(12556565), new Date(254895626));

        System.out.println("Date after " + new Date(230956000));

        dateFlux.subscribe(System.out::println);

        System.out.println("*******");

        dateFlux
            .filter(date -> date.after(new Date(230956000)))
            .subscribe(System.out::println);
    }
}
