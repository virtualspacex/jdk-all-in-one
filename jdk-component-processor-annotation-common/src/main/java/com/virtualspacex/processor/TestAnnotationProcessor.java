package com.virtualspacex.processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

import com.google.auto.service.AutoService;

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = "com.virtualspacex.processor.NameCheck")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TestAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Kind.ERROR, "=================EFFCTIVE==============");
        return false;
    }
    
}