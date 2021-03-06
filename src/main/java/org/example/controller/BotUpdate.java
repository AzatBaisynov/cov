package org.example.controller;

import org.example.bl.FileWriterLogs;
import org.example.dao.HtmlReader;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.example.repo.Article;
import org.telegram.telegrambots.meta.api.objects.User;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

public class BotUpdate {
    public static void runUpdate(Update update) throws FileNotFoundException {
        Bot bot = new Bot();
        if(update.hasCallbackQuery()){


            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            User user = callbackQuery.getFrom();
            FileWriterLogs.writeToLog(user.getId()+ " "+ data + " " + LocalDateTime.now().toString() + "\n");

            Message message1 = callbackQuery.getMessage();
            switch (data) {
                case "/top10" :
                    bot.sendMsgWithAdv(message1, "Вывожу статистику:");
                    List<Article> list = HtmlReader.getOfficialTable();

                    for(int i = 0; i < 10; i++) {
                        bot.sendMsgNoReplyNoButtonTop10(message1,list.get(i).toString());
                    }
                    bot.sendMsgNoReply(message1, "▪ ▫▪ ▫ МЕНЮ ▪ ▫▪ ▫");
                    break;
                case "/kyrgyzstan" :
                    bot.sendMsgWithStatistic(message1, HtmlReader.getCountryOfficialTable("Kyrgyzstan", "Asia").toString());
                    bot.sendMsgNoReply(message1, "▪ ▫▪ ▫ МЕНЮ ▪ ▫▪ ▫");
                    break;
                case "/russia" :
                    bot.sendMsgWithAdv(message1, HtmlReader.getCountryOfficialTable("Russia", "Europe").toString());
                    bot.sendMsgNoReply(message1, "▪ ▫▪ ▫ МЕНЮ ▪ ▫▪ ▫");
                    break;
                case "/kazakhstan" :
                    bot.sendMsgWithAdv(message1, HtmlReader.getCountryOfficialTable("Kazakhstan", "Asia").toString());
                    bot.sendMsgNoReply(message1, "▪ ▫▪ ▫ МЕНЮ ▪ ▫▪ ▫");
                    break;
                case "/uzbekistan" :
                    bot.sendMsgWithAdv(message1, HtmlReader.getCountryOfficialTable("Uzbekistan", "Asia").toString());
                    bot.sendMsgNoReply(message1, "▪ ▫▪ ▫ МЕНЮ ▪ ▫▪ ▫");
                    break;
                case "/china" :
                    bot.sendMsgWithAdv(message1, HtmlReader.getCountryOfficialTable("China", "Asia").toString());
                    break;
                case "/turkey" :
                    bot.sendMsgWithAdv(message1, "Статистика по Турции:");
                    bot.sendMsgNoReply(message1, HtmlReader.getCountryOfficialTable("Turkey", "Asia").toString());
                    break;
                case "/world" :
                    bot.sendMsgWithAdv(message1, "Статистика по миру:\n"+HtmlReader.getCountryOfficialTable("World","All").toString());
                    bot.sendMsgNoReply(message1, "▪ ▫▪ ▫ МЕНЮ ▪ ▫▪ ▫");
                    break;
            }
        } else {
            if (update.getMessage().getText().contains("Меню") ||
                    update.getMessage().getText().contains("меню") ||
                    update.getMessage().getText().contains("Menu") ||
                    update.getMessage().getText().contains("menu") ||
                    update.getMessage().getText().contains("/start")){
                bot.sendMsgNoReply(update.getMessage(),"▪ ▫▪ ▫ МЕНЮ ▪ ▫▪ ▫");
            }
        }
    }
}
