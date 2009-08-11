%
% Buch-Klasse fuer die TU Dresden
% Autor: Klaus Bergmann
%
\def\fileversion{1.0}
\def\filedate{2005/11/20 }
\def\filename{tudbook}

\NeedsTeXFormat{LaTeX2e}  \ProvidesClass{\filename}[\filedate Buch-Klasse fuer die TU Dresden, (c) by Klaus Bergmann]
\typeout{Class: '\filename' Version \fileversion, Buchvorlage der Technischen Universitaet Dresden}

\newif\if@color
\DeclareOption{color}{\@colortrue}

\DeclareOption*{\PassOptionsToClass{\CurrentOption}{book}}
\ProcessOptions\relax
\LoadClass[a4paper]{book}

\RequirePackage{graphicx}
\RequirePackage{ifthen}
\if@color\RequirePackage{xcolor}\fi

% Schriftspezifische Definitionen
\DeclareFixedFont{\univLightIX}{OT1}{aun}{l}{n}{9}
\DeclareFixedFont{\univBoldIX}{OT1}{aun}{b}{n}{9}
\DeclareFixedFont{\univLightXI}{OT1}{aun}{l}{n}{11}
\DeclareFixedFont{\univLightObliqueXI}{OT1}{aun}{l}{sl}{11}
\DeclareFixedFont{\univBoldXI}{OT1}{aun}{b}{n}{11}
\DeclareFixedFont{\univLightVII}{OT1}{aun}{l}{n}{7}
\DeclareFixedFont{\univLightObliqueVII}{OT1}{aun}{l}{sl}{7}

\newcommand*{\dinBold}{\fontencoding{OT1}\fontfamily{din}\fontseries{b}\selectfont}
%\newcommand*{\dinBoldXVI}{\fontencoding{OT1}\fontfamily{din}\fontseries{b}\fontsize{16pt}{19.2pt}\selectfont}

%\renewcommand*{\encodingdefault}{OT1}
\renewcommand*{\familydefault}{aun}
\renewcommand*{\seriesdefault}{l}
\renewcommand*{\shapedefault}{n}
\renewcommand*{\sfdefault}{aun}
\renewcommand*{\sffamily}{\fontfamily{aun}\selectfont}
\renewcommand*{\ttfamily}{\fontfamily{\ttdefault}\fontseries{\mddefault}\selectfont}

% Vom Nutzer anzugebende / veraenderbare Namen und Bezeichnungen
\newcommand*{\@einrichtung}{}
\newcommand*{\@fachrichtung}{}
\newcommand*{\@institut}{}
\newcommand*{\@professur}{}

\if@color
    \definecolor{HKS41-100}{cmyk}{1.00, 0.70, 0.10, 0.60}
    \definecolor{HKS41-030}{cmyk}{0.30, 0.21, 0.03, 0.18}
    \definecolor{HKS41-010}{cmyk}{0.10, 0.07, 0.01, 0.06}
\fi

\newcommand*{\einrichtung}[1]{\renewcommand*{\@einrichtung}{#1}}
\newcommand*{\fachrichtung}[1]{\renewcommand*{\@fachrichtung}{#1}}
\newcommand*{\institut}[1]{\renewcommand*{\@institut}{#1}}
\newcommand*{\professur}[1]{\renewcommand*{\@professur}{#1}}

% Dimensionen
\setlength{\textheight}{238mm}
\setlength{\topmargin}{30mm}
\addtolength{\topmargin}{-1in}
\setlength{\headheight}{0mm}
\setlength{\headsep}{0mm}
\setlength{\topskip}{0mm}
\setlength{\footskip}{17mm}

\setlength{\textwidth}{150mm}
\setlength{\oddsidemargin}{30mm}
\addtolength{\oddsidemargin}{-1in}
\setlength{\evensidemargin}{30mm}
\addtolength{\evensidemargin}{-1in}

\newcommand{\@br}[1][1]{\par\vspace{#1\baselineskip}}

% erste Seite
\newcommand*{\@@dummyComma}{}%

\newcommand{\ps@firstpage}{%
  % Ränder
    \setlength{\topmargin}{-1in}    % <= keinen Rand nach oben
    \setlength{\headheight}{40mm}   % 40mm
    \setlength{\headsep}{49mm}      % 89mm
    \setlength{\topskip}{0mm}       % 89mm
    \setlength{\textheight}{216mm}
    %\setlength{\footskip}{-7mm}
  % Logo und Querbalken
    \renewcommand{\@oddhead}{%
        \if@color\color{white}\fi%
        \newif\if@@profnewline%\@@profnewlinefalse%
        \newif\if@@instnewline%\@@instnewlinefalse%
        \settowidth{\dimen255}{\univBoldIX\@einrichtung\ \univLightIX\@fachrichtung, \@institut, \@professur}%
        \ifdim\dimen255 > 160mm\relax%
            \@@profnewlinetrue%
            \settowidth{\dimen255}{\univBoldIX\@einrichtung\ \univLightIX\@fachrichtung, \@institut}%
            \ifdim\dimen255 > 160mm\relax%
                \@@instnewlinetrue%
                \@@profnewlinefalse%
            \fi%
        \fi%
        \newif\if@@nonewline%
        \if@@instnewline\else\if@@profnewline\else\@@nonewlinetrue\fi\fi%
        \setlength{\tabcolsep}{0mm}%
        \setlength{\arrayrulewidth}{1pt}%
        \newsavebox{\@@professurComma}%
            \sbox{\@@professurComma}{%
            \renewcommand*{\@@dummyComma}{}%
            \ifx\@fachrichtung\@empty\else\renewcommand*{\@@dummyComma}{, }\fi   % Fachrichtung vorhanden -> Komma
            \ifx\@institut\@empty\else\renewcommand*{\@@dummyComma}{, }\fi       % Institut vorhanden -> Komma
            \ifx\@professur\@empty\renewcommand*{\@@dummyComma}{}\fi             % keine Professur vorhanden -> kein Komma
            \if@@profnewline\renewcommand*{\@@dummyComma}{}\fi                   % Professur in neue Zeile -> kein Komma
            \@@dummyComma%
        }%
        \hspace*{-30mm}%
        \begin{tabular*}{210mm}[b]{p{30mm}l@{\extracolsep\fill}p{30mm}}
          % TUD-Logo
            &\hspace*{-19mm}\includegraphics[width=57mm]{TU_Logo_SW}\\[3.75mm]\hline%
          % Einrichtungsname
            &\rule[-1.2mm]{0mm}{13pt}\univBoldIX\@einrichtung%
          % Fachrichtungsname
            \ifx\@fachrichtung\@empty\else\ \univLightIX\@fachrichtung\fi%
          % Komma vor Institutsname (bei Bedarf)
            \renewcommand*{\@@dummyComma}{}%
            \ifx\@institut\@empty\else\renewcommand*{\@@dummyComma}{, }\fi       % Institut vorhanden -> Komma
            \ifx\@fachrichtung\@empty\renewcommand*{\@@dummyComma}{ }\fi         % keine Fachrichtung vorhanden -> kein Komma
            \if@@instnewline\renewcommand*{\@@dummyComma}{}\fi                   % Institut in neue Zeile -> kein Komma
            \@@dummyComma%
          % Institut
            \if@@instnewline\\\hline\fi%
            \if@@instnewline\else\ifx\@institut\@empty\else\univLightIX\@institut\fi\fi%
          % Komma vor Professur (bei Bedarf)
            \if@@profnewline\else\if@@instnewline\else\usebox{\@@professurComma}\fi\fi%
          % Professur
            \if@@profnewline\\\hline\fi%
            \if@@nonewline\ifx\@professur\empty\else\univLightIX\@professur\fi\fi%
            \if@@instnewline\else\if@@profnewline\else\\\hline\fi\fi%
        \end{tabular*}\hspace{-180mm}%
        \parbox[t][0pt]{160mm}{\vspace{0.25\baselineskip}\mbox{%
            \if@@instnewline\ifx\@institut\@empty\else\univLightIX\@institut\fi\usebox{\@@professurComma}\fi%
            \if@@nonewline\else\ifx\@professur\empty\else\univLightIX\@professur\fi\fi%
        }}%
    }%
    \let\@evenhead\@oddhead%
    \let\@oddfoot\@empty\let\@evenfoot\@empty%
}

% restliche Seiten
\if@twoside%
    \renewcommand{\ps@plain}{%
        \let\@oddhead\@empty\let\@evenhead\@empty%
        \renewcommand*{\@oddfoot}{\hfil\univLightIX\thepage}%
        \renewcommand*{\@evenfoot}{\univLightIX\thepage}%
    }%
    \renewcommand{\ps@headings}{%
        \let\@oddhead\@empty\let\@evenhead\@empty%
        \renewcommand*{\@oddfoot}{\hfil\univLightVII\rightmark\quad\univLightIX\thepage}%
        \renewcommand*{\@evenfoot}{\univLightIX\thepage\quad\univLightVII\leftmark\hfil}%
        \def\chaptermark##1{%
            \markboth{%
                \ifnum \c@secnumdepth >\m@ne%
                    \if@mainmatter\@chapapp\ \thechapter\ \ \fi%
                \fi%
            ##1}{}%
        }%
        \def\sectionmark##1{\markright{\ifnum \c@secnumdepth >\z@\thesection\ \ \fi##1}}
    }%
\else%
    \renewcommand{\ps@plain}{%
        \let\@oddhead\@empty\let\@evenhead\@empty%
        \renewcommand*{\@oddfoot}{\hfil\univLightIX\thepage}%
        \let\@evenfoot\@oddfoot%
    }%
    \renewcommand{\ps@headings}{%
        \let\@oddhead\@empty\let\@evenhead\@empty%
        \renewcommand*{\@oddfoot}{\hfil\univLightVII\rightmark\quad\univLightIX\thepage}%
        \let\@evenfoot\@oddfoot%
        \def\chaptermark##1{%
            \markright{%
                \ifnum \c@secnumdepth >\m@ne%
                    \if@mainmatter\@chapapp\ \thechapter\ \ \fi%
                \fi%
            ##1}{}%
        }%
    }%
\fi%
\pagestyle{headings}



\renewcommand*{\part}[1]{
    \addtocounter{part}{1}
 
    \cleardoublepage
    \phantomsection
    \addcontentsline{toc}{part}{#1}

    \pagecolor{HKS41-010}\color{HKS41-100}
    \vspace*{55mm}%
    {\parindent\z@\raggedright\normalfont%
    \interlinepenalty\@M%
    \dinBold\Huge\bfseries{%
        \ifnum \c@secnumdepth >\m@ne\if@mainmatter%
            \begin{list}{}{\setlength{\topsep}{0pt}\setlength{\partopsep}{0pt}\setlength{\parskip}{0pt}\setlength{\parsep}{0pt}%
                           \settowidth{\labelwidth}{\thepart}\setlength{\leftmargin}{2em}%
                           \setlength{\labelsep}{2em}\addtolength{\labelsep}{-\labelwidth}}
                \item[\thepart]\MakeUppercase{#1}%
            \end{list}%
        \fi\fi%
    }\par\nobreak%
    \vskip 2ex}%
    \vfill\pagebreak%
    \pagecolor{white}\color{black}
}



\newif\if@chapterpage
\newcommand*{\chapterpage}{\@chapterpagetrue}

\renewcommand*{\@makechapterhead}[1]{%
    \if@color%
        \if@chapterpage\pagecolor{HKS41-010}\color{HKS41-100}\fi%
    \fi%
    \vspace*{55mm}%
    {\parindent\z@\raggedright\normalfont%
    \interlinepenalty\@M%
    \dinBold\Huge\bfseries{%
        \ifnum \c@secnumdepth >\m@ne\if@mainmatter%
            \begin{list}{}{\setlength{\topsep}{0pt}\setlength{\partopsep}{0pt}\setlength{\parskip}{0pt}\setlength{\parsep}{0pt}%
                           \settowidth{\labelwidth}{\thechapter}\setlength{\leftmargin}{2em}%
                           \setlength{\labelsep}{2em}\addtolength{\labelsep}{-\labelwidth}}
                \item[\thechapter]\MakeUppercase{#1}%
            \end{list}%
        \fi\fi%
    }\par\nobreak%
    \vskip 2ex}%
    \if@chapterpage%
        \@chapterpagefalse%
        \thispagestyle{empty}%
        \vfill\pagebreak%
        \if@color\pagecolor{white}\color{black}\fi%
    \fi%
}%

\renewcommand*{\@makeschapterhead}[1]{%
    \if@color%
        \if@chapterpage\pagecolor{HKS41-010}\color{HKS41-100}\fi%
    \fi%
    \vspace*{55mm}%
    {\parindent\z@\raggedright\normalfont%
    \interlinepenalty\@M%
    \dinBold\Huge\bfseries\MakeUppercase#1\par\nobreak%
    \vskip 2ex}%
    \if@chapterpage%
        \@chapterpagefalse%
        \thispagestyle{empty}%
        \pagebreak%
        \if@color\pagecolor{white}\color{black}\fi%
    \fi%
}%

\renewcommand\section{\@startsection {section}{1}{\z@}%
                                   {-3.5ex \@plus -1ex \@minus -.2ex}%
                                   {2.3ex \@plus.2ex}%
                                   {\dinBold\Large\bfseries\MakeUppercase}}
                                   
\renewcommand{\maketitle}{%
    \def\and{%                  % \begin{tabular}
        \end{tabular}%
        \hskip 1em \@plus.17fil%
        \begin{tabular}[t]{l}%   % \end{tabular}
    }%
    \begin{titlepage}%
        \if@color%
            \pagecolor{HKS41-100}%
            \color{HKS41-030}%
        \fi%
        \thispagestyle{firstpage}\enlargethispage{-59mm}%
        \raggedright%
        {\dinBold\Huge\bfseries\MakeUppercase\@title\par\nobreak}%
        \vskip 1em%
        {\LARGE\bfseries\begin{tabular}[t]{@{}l@{}}\@author\end{tabular}\par}%
        \vskip 3em%
        {\Large\bfseries\@date\par}%
        \@thanks
    \end{titlepage}%
    \if@color%
        \pagecolor{white}%
        \color{black}%
    \fi%
    \setcounter{footnote}{0}%
    \global\let\thanks\relax%
    \global\let\maketitle\relax%
    \global\let\@thanks\@empty%
    \global\let\@author\@empty%
    \global\let\@date\@empty%
    \global\let\@title\@empty%
    \global\let\title\relax%
    \global\let\author\relax%
    \global\let\date\relax%
    \global\let\and\relax%
}%

\newcommand*{\glossaryname}{Befehlsindex}
\newenvironment{theglossary}[1][]{%
    \chapter*{\glossaryname}%
    \@mkboth{\glossaryname}{\glossaryname}\thispagestyle{headings}%
    #1%
    \begin{list}{}{\setlength{\labelsep}{0pt}\setlength{\labelwidth}{0pt}\setlength{\itemindent}{-\leftmargin}}%
}{\end{list}}

\renewenvironment{theindex}[1][]{%
    \columnseprule \z@
    \columnsep 35\p@
    \begin{multicols}2[\@makeschapterhead{\indexname}#1]%
        \@mkboth{\indexname}{\indexname}%
        \thispagestyle{headings}\parindent\z@
        \parskip\z@ \@plus .3\p@\relax
        \let\item\@idxitem%
}{\end{multicols}\clearpage}

\renewenvironment{thebibliography}[1]{%
    \chapter*{\bibname}%
    \@mkboth{\bibname}{\bibname}\thispagestyle{headings}%
    \list{\@biblabel{\@arabic\c@enumiv}}%
         {\settowidth\labelwidth{\@biblabel{#1}}%
            \leftmargin\labelwidth
            \advance\leftmargin\labelsep
            \@openbib@code
            \usecounter{enumiv}%
            \let\p@enumiv\@empty
            \renewcommand\theenumiv{\@arabic\c@enumiv}}%
    \sloppy
    \clubpenalty4000
    \@clubpenalty \clubpenalty
    \widowpenalty4000%
    \sfcode`\.\@m%
}{%
    \def\@noitemerr{\@latex@warning{Leere `thebibliography' Umgebung}}%
    \endlist%
}

\AtBeginDocument{%
    \setlength{\baselineskip}{2.4ex plus0.3ex minus0.2ex}%
    \setlength{\parskip}{\baselineskip}%
    \setlength{\parindent}{0mm}%
    \raggedright%
}
