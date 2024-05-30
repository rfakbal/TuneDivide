from spleeter.separator import Separator
from spleeter.audio.ffmpeg import FFMPEGProcessAudioAdapter
import sys

def separate_audio(input_file, output_dir):
    separator = Separator('spleeter:2stems')
    
    separator.separate_to_file(input_file, output_dir)

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python separate_audio.py <input_file> <output_dir>")
        sys.exit(1)

    input_file = sys.argv[1]
    output_dir = sys.argv[2]
    separate_audio(input_file, output_dir)

