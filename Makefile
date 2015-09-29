#******************************************************************************#
#                                                                              #
#                                                         :::      ::::::::    #
#    Makefile                                           :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: mcanal <mcanal@student.42.fr>              +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2014/11/29 13:16:03 by mcanal            #+#    #+#              #
#    Updated: 2015/09/29 22:59:26 by mcanal           ###   ########.fr        #
#                                                                              #
#******************************************************************************#

P_NAME = Predict
T_NAME = Train

P_SRC = Predict.scala
T_SRC =	Train.scala

P_DIR = src
T_DIR = src
VPATH =	src
O_DIR = obj

P_SRCC = $(addprefix $(P_DIR)/,$(P_SRC))
T_SRCC = $(addprefix $(T_DIR)/,$(T_SRC))
P_SRCO = $(P_SRC:%.scala=$(O_DIR)/%.class)
T_SRCO = $(T_SRC:%.scala=$(O_DIR)/%.class)
OBJS =	$(P_SRCO) $(T_SRCO)

CFLAGS =	-deprecation -encoding UTF-8 -unchecked -explaintypes \
			-Ywarn-dead-code -Ywarn-value-discard -Ywarn-numeric-widen \
			-Xcheckinit -Xfatal-warnings #-Xcheck-null -feature
RM = rm -rf
MKDIR = mkdir -p
I_DIR =

ifeq ($(shell uname), Linux)
SC = /home/mcanal/Downloads/scala-2.11.7/bin/scalac
S = /home/mcanal/Downloads/scala-2.11.7/bin/scala
else
SC = ~/scala/bin/scalac
S = ~/scala/bin/scala
endif

WHITE = \033[37;01m
RED = \033[31;01m
GREEN =  \033[32;01m
BLUE =  \033[34;01m
BASIC = \033[0m

.PHONY: all predict train clean fclean re

all: $(T_NAME) $(P_NAME) $(D_NAME)
	@echo "$(WHITE)compi:$(BASIC) $(SC)"
	@echo "$(WHITE)flags:$(BASIC) $(CFLAGS)"

$(T_NAME): $(T_SRCO)
	@echo "$(BLUE)$< $(WHITE)->$(RED) $@ $(BASIC)"
	@echo "$(S) -classpath $(O_DIR) $@" > $@ && chmod 755 $@

$(P_NAME): $(P_SRCO)
	@echo "$(BLUE)$< $(WHITE)->$(RED) $@ $(BASIC)"
	@echo "$(S) -classpath $(O_DIR) $@" > $@ && chmod 755 $@
	@open https://docs.google.com/spreadsheets/d/1E4vBq57bbtA1dVX4Qz0vfHpyaV0i1IWrI2HO09IdlRk/edit#gid=1611162065

$(T_SRCO): $(T_SRCC)
	@echo "$(WHITE)$< ->$(BLUE) $@ $(BASIC)"
	@$(SC) $(CFLAGS) $< -d $(O_DIR)

$(P_SRCO): $(P_SRCC)
	@echo "$(WHITE)$< ->$(BLUE) $@ $(BASIC)"
	@$(SC) $(CFLAGS) $< -d $(O_DIR)

$(OBJS): | $(O_DIR)

$(O_DIR):
	@$(MKDIR) $(O_DIR)

clean:
	@$(RM) $(O_DIR)

fclean: clean
	@$(RM) $(P_NAME)
	@$(RM) $(T_NAME)

re: fclean all
