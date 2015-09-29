#******************************************************************************#
#                                                                              #
#                                                         :::      ::::::::    #
#    Makefile                                           :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: mcanal <mcanal@student.42.fr>              +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2014/11/29 13:16:03 by mcanal            #+#    #+#              #
#    Updated: 2015/09/29 00:17:15 by mcanal           ###   ########.fr        #
#                                                                              #
#******************************************************************************#

P_NAME = Predict
T_NAME = Train
D_NAME = Draw

P_SRC = Predict.scala
T_SRC =	Train.scala
D_SRC =	Draw.scala

P_DIR = src
T_DIR = src
D_DIR = src
VPATH =	src
O_DIR = obj

P_SRCC = $(addprefix $(P_DIR)/,$(P_SRC))
T_SRCC = $(addprefix $(T_DIR)/,$(T_SRC))
D_SRCC = $(addprefix $(D_DIR)/,$(D_SRC))
P_SRCO = $(P_SRC:%.scala=$(O_DIR)/%.class)
T_SRCO = $(T_SRC:%.scala=$(O_DIR)/%.class)
D_SRCO = $(D_SRC:%.scala=$(O_DIR)/%.class)
OBJS =	$(P_SRCO) $(T_SRCO) $(D_SRCO)

I_DIR =	-classpath /home/mcanal/Downloads/scala-2.11.7/scala-swing_2.11-1.0.2.jar /home/mcanal/Downloads/jfreechart-1.0.1/lib/jcommon-1.0.0.jar /home/mcanal/Downloads/jfreechart-1.0.1/lib/jfreechart-1.0.1.jar
#/home/mcanal/Downloads/jfreechart-1.0.19/lib/jcommon-1.0.23.jar /home/mcanal/Downloads/jfreechart-1.0.19/lib/jfreechart-1.0.19.jar
CFLAGS =	-deprecation -encoding UTF-8 -unchecked -explaintypes \
			-Ywarn-dead-code -Ywarn-value-discard -Ywarn-numeric-widen \
			-Xcheckinit -Xfatal-warnings #-Xcheck-null -feature
RM = rm -rf
MKDIR = mkdir -p

ifeq ($(shell uname), Linux)
SC = /home/mcanal/Downloads/scala-2.11.7/bin/scalac
S = /home/mcanal/Downloads/scala-2.11.7/bin/scala
else
SC = scalac
S = scala
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

$(D_NAME): $(D_SRCO)
	@echo "$(BLUE)$< $(WHITE)->$(RED) $@ $(BASIC)"
	@echo "$(S) -classpath $(O_DIR) $@" > $@ && chmod 755 $@

$(T_SRCO): $(T_SRCC)
	@echo "$(WHITE)$< ->$(BLUE) $@ $(BASIC)"
	@$(SC) $(CFLAGS) $< -d $(O_DIR)

$(P_SRCO): $(P_SRCC)
	@echo "$(WHITE)$< ->$(BLUE) $@ $(BASIC)"
	@$(SC) $(CFLAGS) $< -d $(O_DIR)

$(D_SRCO): $(D_SRCC)
	@echo "$(WHITE)$< ->$(BLUE) $@ $(BASIC)"
	@$(SC) $(CFLAGS) $(I_DIR) $< -d $(O_DIR)

$(OBJS): | $(O_DIR)

$(O_DIR):
	@$(MKDIR) $(O_DIR)

clean:
	@$(RM) $(O_DIR)

fclean: clean
	@$(RM) $(P_NAME)
	@$(RM) $(T_NAME)

re: fclean all
