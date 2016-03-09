package com.cronutils;

import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;
import org.quartz.CronExpression;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * A Spring formatter used to format a Quartz cron expressions.
 *
 * <p>Sample usage</p>
 *
 * <blockquote><code>
 * 	&lt;bean id="cronDescriptionService"
 *		class="org.springframework.format.support.FormattingConversionServiceFactoryBean"&gt;
 *		&lt;property name="formatters"&gt;
 *			&lt;set&gt;
 *				&lt;bean class="com.cronutils.QuartzCronExpressionFormatter"/&gt;
 *			&lt;/set&gt;
 *		&lt;/property&gt;
 * &lt;/bean&gt;
 * </pre></code></blockquote>
 *
 * In ThymeLeaf, cron expression can be displayed as <code>${{cronExpression}}</code>.
 */
public class QuartzCronExpressionFormatter implements Formatter<CronExpression> {
    private CronParser parser;

    public QuartzCronExpressionFormatter(){
        parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ));
    }


    public String print(CronExpression cron, Locale locale) {
        String pattern = cron.getCronExpression();
        try{
            return CronDescriptor.instance(locale).describe(parser.parse(pattern));
        }catch (Exception e){
            return pattern;
        }
    }

    public CronExpression parse(String text, Locale locale) throws ParseException {
        throw new UnsupportedOperationException("Parsing human readable string to cron is not supported.");
    }
}
