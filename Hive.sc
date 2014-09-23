Hive {

	// Hive 1.0

	classvar notesMelody;
	classvar notesMelodyreplace;
	classvar notesHarmony;
	classvar notesBass;
	classvar notesTexture;
	classvar rootMelody;
	classvar rootHarmony;
	classvar rootBass;
	classvar rootTexture;
	classvar melodyStart;
	classvar melodyStop;
	classvar harmonyStart;
	classvar harmonyStop;
	classvar bassStop;
	classvar bassStart;
	classvar textureStart;
	classvar textureStop;
	classvar melodyEnv;
	classvar harmonyEnv;
	classvar bassEnv;
	classvar textureEnv;
	classvar shiftMelody;
	classvar shiftHarmony;
	classvar shiftBass;
	classvar shiftTexture;



	*boot {
		Server.default = Server.internal.boot;
		notesMelody = [0];
		notesMelodyreplace = [0];
		notesHarmony = [0];
		notesBass = [0];
		notesTexture = [0];
		rootMelody = 0;
		rootHarmony = 0;
		rootBass = 0;
		rootTexture = 0;
		shiftMelody = 0;
		shiftHarmony = 0;
		shiftBass = 0;
		shiftTexture = 0;

		Server.default.waitForBoot( {
			 //Hive.melody;
			 //Hive.harmony;
			 //Hive.bass;
			 //Hive.texture;
		});
	}




// key shifts

	*shiftMelody {
		|keyChange1|
		shiftMelody = keyChange1;
		Pbindef(\sineOne,\midinote,Pseq(notesMelody+rootMelody+shiftMelody,inf),\db,Pseq([shiftMelody],inf));
	}


	*shiftHarmony {
		|keyChange2|
		shiftHarmony = keyChange2;
		Pbindef(\triOne,\midinote,Pseq(notesHarmony+rootHarmony+shiftHarmony,inf),\db,Pseq([shiftHarmony],inf));
	}


	*shiftBass {
		|keyChange3|
		shiftBass = keyChange3;
		Pbindef(\bassOne,\midinote,Pseq(notesBass+rootBass+shiftBass,inf),\db,Pseq([shiftBass],inf));
	}


	*shiftTexture {
		|keyChange4|
		shiftTexture = keyChange4;
		Pbindef(\sawOne,\midinote,Pseq(notesTexture+rootTexture+shiftTexture,inf),\db,Pseq([shiftTexture],inf));
	}






// notes

	*notesMelody {
		|ns|
		notesMelody = ns;
		Pbindef(\sineOne,\midinote,Pseq(notesMelody+rootMelody,inf));
	}


	*notesHarmony {
		|harmonynotes|
		notesHarmony = harmonynotes;
		Pbindef(\triOne,\midinote,Pseq(notesHarmony+rootHarmony,inf));
	}


	*notesBass {
		|bassnotes|
		notesBass = bassnotes;
		Pbindef(\bassOne,\midinote,Pseq(notesBass+rootBass,inf));
	}


	*notesTexture {
		|sawnotes|
		notesTexture = sawnotes;
		Pbindef(\sawOne,\midinote,Pseq(notesTexture+rootTexture,inf));
	}







// keys

	*rootMelody {
		|keysine|
		rootMelody = keysine;
		Pbindef(\sineOne,\midinote,Pseq(notesMelody+rootMelody,inf));
	}


	*rootHarmony {
		|keytri|
		rootHarmony = keytri;
		Pbindef(\triOne,\midinote,Pseq(notesHarmony+rootHarmony,inf));
	}


	*rootBass {
		|keybass|
		rootBass = keybass;
		Pbindef(\bassOne,\midinote,Pseq(notesBass+rootBass,inf));
	}


	 *rootTexture {
		|keysaw|
		rootTexture = keysaw;
		Pbindef(\sawOne,\midinote,Pseq(notesTexture+rootTexture,inf));
	}




// tempo

	*tempo {
		|tempoChange|
		TempoClock.default.tempo = tempoChange/60;
		"The tempo is set in BPM".postln;
	}




// start & stop parameters

	*melodyStart {
		|melodyBegin|
		melodyStart = melodyBegin;
		"Melody started".postln;
		Pbindef(\sineOne,\midinote).play
	}


	*melodyStop {
		|melodyEnd|
		melodyStop = melodyEnd;
		"Melody stopped".postln;
		Pbindef(\sineOne,\midinote).stop
	}


	*harmonyStart {
		|harmonyBegin|
		harmonyStart = harmonyBegin;
		"Harmony started".postln;
		Pbindef(\triOne,\midinote).play
	}


	*harmonyStop {
		|harmonyEnd|
		harmonyStop = harmonyEnd;
		"Harmony stopped".postln;
		Pbindef(\triOne,\midinote).stop
	}


	*bassStart {
		|bassdrumBegin|
		bassStart = bassdrumBegin;
		"Bass started".postln;
		Pbindef(\bassOne,\midinote).play
	}


	*bassStop {
		|bassdrumEnd|
		bassStop = bassdrumEnd;
		"Bass stopped".postln;
		Pbindef(\bassOne,\midinote).stop
	}


	*textureStart {
		|sawBegin|
		textureStart = sawBegin;
		"Texture started".postln;
		Pbindef(\sawOne,\midinote).play
	}


	*textureStop {
		|sawEnd|
		textureStop = sawEnd;
		"Texture stopped".postln;
		Pbindef(\sawOne,\midinote).stop
	}



// attack & release changes

	*melodyEnv {
		|sineEnve|
		~melodyEnv = sineEnve;
		if(sineEnve.asString == "long",{Pbindef(\sineOne,\atk,0.5,\rel,2.5)});
		if(sineEnve.asString == "short",{Pbindef(\sineOne,\atk,0.1,\rel,0.1)});
		if(sineEnve.asString == "perc",{Pbindef(\sineOne,\atk,0.001,\rel,1.0)});
		if(sineEnve.asString == "rev",{Pbindef(\sineOne,\atk,1.0,\rel,0.001)});
		if(sineEnve.asString == "tri",{Pbindef(\sineOne,\atk,0.5,\rel,0.5)});
	}




	*harmonyEnv {
		|triEnve|
		~harmonyEnv = triEnve;
		if(triEnve.asString == "long",{Pbindef(\triOne,\atk,0.5,\rel,2.5)});
		if(triEnve.asString == "short",{Pbindef(\triOne,\atk,0.1,\rel,0.1)});
		if(triEnve.asString == "perc",{Pbindef(\triOne,\atk,0.001,\rel,1.0)});
		if(triEnve.asString == "rev",{Pbindef(\triOne,\atk,1.0,\rel,0.001)});
		if(triEnve.asString == "tri",{Pbindef(\triOne,\atk,0.5,\rel,0.5)});
	}




	*bassEnv {
		|bassEnve|
		~bassEnv = bassEnve;
		if(bassEnve.asString == "long",{Pbindef(\bassOne,\atk,0.5,\rel,2.5)});
		if(bassEnve.asString == "short",{Pbindef(\bassOne,\atk,0.1,\rel,0.1)});
		if(bassEnve.asString == "perc",{Pbindef(\bassOne,\atk,0.001,\rel,1.0)});
		if(bassEnve.asString == "rev",{Pbindef(\bassOne,\atk,1.0,\rel,0.001)});
		if(bassEnve.asString == "tri",{Pbindef(\bassOne,\atk,0.5,\rel,0.5)});
	}




	*textureEnv {
		|sawEnve|
		~textureEnv = sawEnve;
		if(sawEnve.asString == "long",{Pbindef(\sawOne,\atk,0.5,\rel,2.5)});
		if(sawEnve.asString == "short",{Pbindef(\sawOne,\atk,0.1,\rel,0.1)});
		if(sawEnve.asString == "perc",{Pbindef(\sawOne,\atk,0.001,\rel,1.0)});
		if(sawEnve.asString == "rev",{Pbindef(\sawOne,\atk,1.0,\rel,0.001)});
		if(sawEnve.asString == "tri",{Pbindef(\sawOne,\atk,0.5,\rel,0.5)});
	}





	*melody {

				SynthDef.new(\sine, {
					arg freq = 440, atk = 0.005, rel = 0.3, dur = 0.5, amp = 1, legato = 1.0; //pan = 0;
					var sig;
					sig = LFTri.ar(freq);
					sig = sig  * EnvGen.kr(Env.new([0,1,0],[atk,rel],[1,-1]),doneAction:2);
			sig = Pan2.ar(sig, amp);
					Out.ar(0, sig);
				}).add;


				Pdef(\sineOne,
					Pbind(
						\instrument, \sine,
						\dur, 0.5,
						\midinote, 0,
						\legato, 1.0,
						\atk, 0.1,
						\rel, 0.5,
						\db, -22,

					);
				).play;

		"You have added the melody instrument, it is a sine wave oscillator.".postln;
	}



	*harmony {

			SynthDef.new(\tri, {
				arg freq = 440, atk = 0.005, rel = 0.3, amp = 1, dur = 0.5, legato = 1, pan = 0;
				var sig;
				sig = LFTri.ar(freq);
				sig = sig  * EnvGen.kr(Env.new([0,1,0],[atk,rel],[1,-1]),doneAction:2);
			sig = Pan2.ar(sig, amp);
				Out.ar(0, sig);
			}).add;


			Pdef(\triOne,
				Pbind(
					\instrument, \tri,
					\dur, 0.5,
					\midinote, 0,
					\legato, 1.0,
					\atk, 0.1,
					\rel, 0.5,
					\db, -15,

				);
			).play;

		"You have added the harmony instrument, it is a triangle wave oscillator.".postln;

	}



	*bass {

		SynthDef(\bassOne, {
			arg freq = 440, atk = 0.010, rel = 0.015, amp = 1, dur = 0.5, pan = 0;
			var sig;
			sig = SinOscFB.ar(freq);
			sig = sig  * EnvGen.kr(Env.new([0,1,0],[atk,rel],[1,-1]),doneAction:2);
			sig = Pan2.ar(sig, amp);
			Out.ar(0, sig);
		}).add;


		Pdef(\bassOne,
			Pbind(
				\instrument, \bassOne,
				\dur, 0.5,
				\midinote, 0,
				\atk, 0.010,
				\rel, 0.015,
				\db, 10,
			);
		).play;

		"You have added the bass drum instrument, it is a sine wave oscillator.".postln;

	}



	 *texture {

			SynthDef.new(\saw, {
				arg freq = 440, atk = 0.005, rel = 0.3, amp = 1, dur = 1, legato = 2;
			//, pan = 0;
				var sig;
				sig = Saw.ar(freq);
			sig = sig  * EnvGen.kr(Env.new([0,1,0],[atk,rel],[1,-1]),doneAction:2);
			sig = Pan2.ar(sig, amp);
				Out.ar(0, sig);
		}).add;


			Pdef(\sawOne,
				Pbind(
					\instrument, \saw,
					\dur,  1,
					\midinote, 0,
					\legato, 2.5,
					\atk, 2.0,
					\rel, 3.0,
					\db, -28,
				);
			).play;

		"You have added the texture instrument, it is a sawtooth wave oscillator.".postln;

	}




	*help {
		"Please begin by reading Hive's documentation.".postln;
		"1. As a start, begin by evaluating Hive.boot".postln;
		"You can add an instrument by booting it.".postln;
		"For example, evaluate the following: Hive.texture".postln;
		"2. You can also add a specific root to a instrument.".postln;
		"For example, Hive.rootTexture(50)".postln;
		"3. You can change the root, and add notes as well.".postln;
		"To add notes to the texture instrument, you do the following:".postln;
		"Hive.notesTexture([0,7,4])".postln;
		"5. You do not need to follow the notes outlined in the above example.".postln;
		"But it is wise to keep the first number of notes set a 0.".postln;
		"You are free to experiment and use other notes like ([20,30,35])".postln;
		"But you will find it does not sound as appealing acoustically.".postln;
		"So far we have the following code:".postln;
		"Hive.boot".postln;
		"Hive.texture".postln;
		"Hive.rootTeture(50)".postln;
		"Hive.notesTexture([0,7,4])".postln;
		"You can also add different effects to the notes and the root.".postln;
		"These kinds of examples are outlined in more detail within the documentation.".postln;
		"7. You can also change the tempo of what is being played.".postln;
		"You do this by evaluating: Hive.tempo(60) which gives you a tempo of 60 BPM.".postln;
		"You have the freedom to change the tempo how you see fit.".postln;
	}



	*doesNotUnderstand {

		|sel|

		("I do not understand." ++ sel.asString).postln;

		if(sel.asString == "rootMelod",{"Did you mean 'rootMelody'?".postln;});

		if(sel.asString == "rootHarmo",{"Did you mean 'rootHarmony'?".postln;});

		if(sel.asString == "rootBas",{"Did you mean 'rootBass'?".postln;});

		if(sel.asString == "rootTextu",{"Did you mean 'rootTexture'?".postln;});

		if(sel.asString == "harmonyEn",{"Did you mean 'harmonyEnv'?".postln;});

		if(sel.asString == "harmonyenv",{"Note: the 'e' in 'Env' needs to be uppercase!".postln;});

		if(sel.asString == "textureEn",{"Did you mean 'textureEnv'?".postln;});

		if(sel.asString == "bassEn",{"Did you mean 'bassEnv'?".postln;});

		if(sel.asString == "notesmelody",{"Note: the 'm' in 'notesMelody' needs to be uppercase!".postln;});

}
}