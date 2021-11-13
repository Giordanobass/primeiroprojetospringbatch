package com.udemy.primeiroprojetospringbatch.step;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimeParImparStep {
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    public Step imprimeParImparStep() {
	return stepBuilderFactory.get("imprimeParImparStep").<Integer, String>chunk(1).reader(contaAteDezReader())
		.processor(parOuImparProcessor()).writer(imprimeWriter()).build();
    }

    private ItemWriter<String> imprimeWriter() {
	return itens -> itens.forEach(System.out::println);
    }

    private FunctionItemProcessor<Integer, String> parOuImparProcessor() {
	return new FunctionItemProcessor<>(
		item -> item % 2 == 0 ? String.format("Item %s é Par", item) : String.format("Item %s é Impar", item));
    }

    private ItemReader<Integer> contaAteDezReader() {
	List<Integer> numerosDeUmAteDez = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	return new IteratorItemReader<Integer>(numerosDeUmAteDez.iterator());
    }


}
