#alguma documentacao
FILE	= main
BIBFILE = main


all : compile
	evince  $(FILE).pdf

compile:
	pdflatex $(FILE).tex
	bibtex $(BIBFILE) 
	pdflatex $(FILE).tex
	pdflatex $(FILE).tex
	make clean 

html: $(FILE).html

clean :	
	-rm -f *~ *.aux *.log *.dvi *.ps *.bbl *.blg *.out *.snm *.toc *.nav *.idx *.ilg *.ind *.lot *.lof *.out *.nls *.nlo 

help:
	@echo "make clean: \ndelete *~ *.aux *.log *.dvi *.ps *.bbl *.blg *.out *.snm *.toc *.nav *.idx *.ilg *.ind *.lot *.lof *.out files and *.pdf\n"

