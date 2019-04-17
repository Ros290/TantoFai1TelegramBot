package main.bot;

import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

public class Bot {

	private static final String TOKEN = "351992088:AAGD410aeUAaTIrATtCm3Y8lw56X9Y4RMYk";
	
	
	public static void main(String[] args) {
		TelegramBot bot = new TelegramBot(TOKEN);
		int m = 0;
		while (true) {
			List<Update> updates = bot.execute(new GetUpdates().limit(100).offset(m)).updates();
			for (Update update : updates) {
				m = update.updateId() + 1;
				String msgReturn = "";
				if (update.message() != null) {
					bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
					Message msg = update.message();
					//System.out.println(msg.contact().firstName());
					//System.out.println(msg.contact().userId());
					System.out.println(msg.from().username());
					System.out.println(msg.from().id());
					if (!msg.from().username().isEmpty()) 
						msgReturn = msg.from().username();
					else if (!msg.from().firstName().isEmpty())
						msgReturn = msg.from().firstName();
					else
						msgReturn = "Coso";
					msgReturn += " ha scritto qualcosa";
					/*
					if (msg.from().id() == 106656880)
						msgReturn = "Questo è Mattia";
					if (msg.from().id() == 39090721)
						msgReturn = "Questa è Marzia";
					if (msg.from().id() == 171271615)
						msgReturn = "Questo è ricchi";
						*/
					//update.message().chat();
					/*
					if (msg.text().isEmpty())
						msgReturn = "vuoto";
					else msgReturn = "non vuoto";
					*/
					bot.execute(new SendMessage(update.message().chat().id(), msgReturn));
				}

			}
		}
	}
}
