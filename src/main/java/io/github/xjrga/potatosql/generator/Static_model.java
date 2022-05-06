package io.github.xjrga.potatosql.generator;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

public enum Static_model {
    string_utils(),;

    private final TemplateHashModel model;

    Static_model() {
        BeansWrapper wrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_29).build();
        model = wrapper.getStaticModels();
    }

    public TemplateHashModel instance() {
        TemplateHashModel thm = null;
        try {
            switch (this) {
                case string_utils:
                    thm = (TemplateHashModel) model.get("freemarker.template.utility.StringUtil");
                    break;
            }
        } catch (TemplateModelException ex) {
        }
        return thm;
    }

}
