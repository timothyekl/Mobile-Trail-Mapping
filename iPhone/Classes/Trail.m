#import "Trail.h"

@implementation Trail

@synthesize name = _name;
@synthesize trailPoints = _trailPoints;
@synthesize trailHeads = _trailHeads;
@synthesize trailPaint = _trailPaint;

-(id) initWithName:(NSString *)name {
    
	self = [super init];
	if (self != nil) {
        _name = name;
        _trailPoints = [NSMutableArray init];
        _trailHeads = [NSMutableArray init];
        _trailPaint = [UIColor yellowColor];
	}
	return self;
}

- (void) dealloc {
    
    [_name release];
    [_trailPoints release];
    [_trailHeads release];
    [_trailPaint release];
	[super dealloc];
}

@end
