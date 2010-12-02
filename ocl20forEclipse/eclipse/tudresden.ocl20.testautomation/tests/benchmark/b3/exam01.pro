use> open exam.use

use> !create ada,bob,cyd,dan,eve:Person

use> -- Exam(examinee,examiner,recorder)
use> !create ada_bob_cyd:Exam between (ada,bob,cyd)
use> !set ada_bob_cyd.date:=20001023

use> ?ada_bob_cyd.examinee
     @ada : Person
use> ?ada_bob_cyd.examiner
     @bob : Person
use> ?ada_bob_cyd.recorder
     @cyd : Person

use> !create dan_bob_ada:Exam between (dan,bob,ada)
use> !set dan_bob_ada.date:=20040817

use> !create eve_ada_dan:Exam between (eve,ada,dan)
use> !set eve_ada_dan.date:=20080331

use> ?Exam.allInstances->select(e|e.examinee=ada)
     Set{@ada_bob_cyd} : Set(Exam)
use> ?Exam.allInstances->select(e|e.recorder=ada)
     Set{@dan_bob_ada} : Set(Exam)
use> ?Exam.allInstances->select(e|e.examiner=ada)
     Set{@eve_ada_dan} : Set(Exam)

use> ?ada.examinee[examiner]
     Set{@eve} : Set(Person)
use> ?ada.examinee[recorder]
     Set{@dan} : Set(Person)
use> ?ada.examiner[examinee]
     Set{@bob} : Set(Person)
use> ?ada.examiner[recorder]
     Set{@bob} : Set(Person)
use> ?ada.recorder[examinee]
     Set{@cyd} : Set(Person)
use> ?ada.recorder[examiner]
     Set{@dan} : Set(Person)

use> !create flo:Person
use> !create flo_ada_dan:Exam between (flo,ada,dan)
use> !set flo_ada_dan.date:=20080331

use> ?ada.recorder[examiner]
     Set{@dan} : Set(Person)

use> ?Exam.allInstances->select(e|e.examiner=ada)->collect(e|e.recorder)
     Bag{@dan,@dan} : Bag(Person)
