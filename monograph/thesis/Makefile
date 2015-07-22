FILE	= main

all : compile
	evince  $(FILE).pdf

compile:
	pdflatex $(FILE).tex
	pdflatex $(FILE).tex
	bibtex $(FILE)
	makeindex $(FILE).nlo -s nomencl.ist -o $(FILE).nls
	pdflatex $(FILE).tex
	make clean 
clean :	
	-rm -f *.synctex.gz *.ist *~ *.aux *.log *.dvi *.ps *.bbl *.blg *.out *.glsdefs *.snm *.mw *.toc *.nav *.idx *.ilg *.ind *.loq *.los *.lot *.lof *.lol *.loa *.glo *.brf *.out *.nls *.nlo 

help:
	@echo "make compile: to compile and build the final PDF\n"
	@echo "make clean: to delete aux files \nrm -f *.synctex.gz *.ist *~ *.aux *.log *.dvi *.ps *.bbl *.blg *.out *.glsdefs *.snm *.mw *.toc *.nav *.idx *.ilg *.ind *.loq *.los *.lot *.lof *.lol *.loa *.glo *.brf *.out *.nls *.nlo"

